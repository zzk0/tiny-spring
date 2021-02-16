package top.zzk0.context;

import top.zzk0.ioc.BeanDefinition;
import top.zzk0.factory.AbstractBeanFactory;
import top.zzk0.factory.AutowireCapableBeanFactory;
import top.zzk0.io.ResourceLoader;
import top.zzk0.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String xmlPath;

    public ClassPathXmlApplicationContext(String xmlPath) throws Exception {
        this(new AutowireCapableBeanFactory(), xmlPath);
    }

    public ClassPathXmlApplicationContext(AbstractBeanFactory factory, String xmlPath) throws Exception {
        super(factory);
        this.xmlPath = xmlPath;
        fresh();
    }

    @Override
    protected void fresh() throws Exception {
        // 读取 xml
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinition(xmlPath);

        // 放入 factory
        for (Map.Entry<String, BeanDefinition> entry : reader.getRegistry().entrySet()) {
            factory.register(entry.getKey(), entry.getValue());
        }
    }
}
