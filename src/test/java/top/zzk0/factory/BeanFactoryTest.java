package top.zzk0.factory;

import top.zzk0.bean.Dog;
import org.junit.jupiter.api.Test;
import top.zzk0.ioc.BeanDefinition;
import top.zzk0.context.ApplicationContext;
import top.zzk0.context.ClassPathXmlApplicationContext;
import top.zzk0.io.ResourceLoader;
import top.zzk0.xml.XmlBeanDefinitionReader;

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
        Dog dog = (Dog)factory.getBean("dog");
        dog.say();
    }

    @Test
    public void contextTest() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("tiny-spring.xml");

        // 取出 dog
        Dog dog = (Dog)context.getBean("dog");
        dog.say();
    }
}
