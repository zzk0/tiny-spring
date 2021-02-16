package top.zzk0.factory;

import top.zzk0.ioc.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {

    Map<String, BeanDefinition> beans;

    public AbstractBeanFactory() {
        beans = new ConcurrentHashMap<>();
    }

    public Object getBean(String name) throws Exception {
        // lazy-init, 用到的时候再初始化
        BeanDefinition definition = beans.get(name);
        if (definition == null) {
            System.out.println("No Such Bean");
            return null;
        }
        Object bean = definition.getBean();
        if (bean == null) {
            bean = doCreateBean(definition);
        }
        return bean;
    }

    public void register(String name, BeanDefinition definition) {
        beans.put(name, definition);
    }

    public void preInstantiateSingletons() throws Exception {
        for (Map.Entry<String, BeanDefinition> entry : beans.entrySet()) {
            getBean(entry.getKey());
        }
    }

    abstract protected Object doCreateBean(BeanDefinition definition) throws Exception;
}
