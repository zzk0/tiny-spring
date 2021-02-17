package top.zzk0.aop;

import top.zzk0.ioc.factory.BeanFactory;

public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory);
}
