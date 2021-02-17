package top.zzk0.ioc.factory;

import top.zzk0.bean.Animal;
import top.zzk0.bean.Dog;
import org.junit.jupiter.api.Test;
import top.zzk0.ioc.BeanDefinition;
import top.zzk0.ioc.context.ApplicationContext;
import top.zzk0.ioc.context.ClassPathXmlApplicationContext;
import top.zzk0.ioc.io.ResourceLoader;
import top.zzk0.ioc.xml.XmlBeanDefinitionReader;

import java.util.Map;

class BeanFactoryTest {

    @Test
    public void basicTest() throws Exception {
        // 读取 xml
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinition("tiny-spring.xml");

        // 放入 factory
        BeanFactory factory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> entry : reader.getRegistry().entrySet()) {
            factory.register(entry.getKey(), entry.getValue());
        }

        // 取出 dog
        Animal dog = (Animal)factory.getBean("dog");
        dog.say();
        dog.walk();
    }

    @Test
    public void contextTest() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("tiny-spring.xml");

        // 取出 dog
        Animal dog = (Animal)context.getBean("dog");
        dog.say();
        dog.walk();
    }
}
