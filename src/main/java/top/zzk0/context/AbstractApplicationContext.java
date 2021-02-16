package top.zzk0.context;

import top.zzk0.ioc.BeanDefinition;
import top.zzk0.factory.AbstractBeanFactory;

public class AbstractApplicationContext implements ApplicationContext {

    protected AbstractBeanFactory factory;

    public AbstractApplicationContext(AbstractBeanFactory factory) {
        this.factory = factory;
    }

    @Override
    public Object getBean(String name) throws Exception {
        return factory.getBean(name);
    }

    @Override
    public void register(String name, BeanDefinition definition) throws Exception {

    }

    protected void fresh() throws Exception {}
}
