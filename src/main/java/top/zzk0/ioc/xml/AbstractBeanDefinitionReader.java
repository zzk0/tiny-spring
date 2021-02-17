package top.zzk0.ioc.xml;

import top.zzk0.ioc.BeanDefinition;
import top.zzk0.ioc.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry;
    private ResourceLoader loader;

    public AbstractBeanDefinitionReader(ResourceLoader loader) {
        this.registry = new HashMap<>();
        this.loader = loader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getLoader() {
        return loader;
    }
}
