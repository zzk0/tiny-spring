package top.zzk0.ioc.factory;

import org.aopalliance.aop.Advice;
import top.zzk0.aop.BeanFactoryAware;
import top.zzk0.aop.aspectj.Advisor;
import top.zzk0.aop.aspectj.AspectJExpressionPointCutAdvisor;
import top.zzk0.ioc.BeanDefinition;
import top.zzk0.ioc.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {

    Map<String, BeanDefinition> beans;
    List<String> advisor;
    List<String> postprocessors;

    public AbstractBeanFactory() {
        beans = new ConcurrentHashMap<>();
        advisor = new ArrayList<>();
        postprocessors = new ArrayList<>();
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
            bean = postProcess(name, bean);
            definition.setBean(bean);
        }
        return bean;
    }

    public void register(String name, BeanDefinition definition) throws Exception {
        beans.put(name, definition);
        if (definition.getBeanClass().isAssignableFrom(AspectJExpressionPointCutAdvisor.class)) {
            advisor.add(name);
        }
        for (Class oneInterface : definition.getBeanClass().getInterfaces()) {
            if (oneInterface == BeanPostProcessor.class) {
                postprocessors.add(name);
            }
        }
    }

    public void preInstantiateSingletons() throws Exception {
        for (Map.Entry<String, BeanDefinition> entry : beans.entrySet()) {
            getBean(entry.getKey());
        }
    }

    public List<String> getAdvisor() {
        return advisor;
    }

    private Object postProcess(String name, Object bean) throws Exception {
        if (bean instanceof BeanFactoryAware) return bean;
        if (bean instanceof BeanPostProcessor) return bean;
        if (bean instanceof Advisor) return bean;
        if (bean instanceof Advice) return bean;
        for (String processorName : postprocessors) {
            BeanPostProcessor postProcessor = (BeanPostProcessor)getBean(processorName);
            bean = postProcessor.postProcessBeforeInitialization(name, bean);

            // Initialization Code here. no yet

            bean = postProcessor.postProcessAfterInitialization(name, bean);
        }
        return bean;
    }

    abstract protected Object doCreateBean(BeanDefinition definition) throws Exception;
}
