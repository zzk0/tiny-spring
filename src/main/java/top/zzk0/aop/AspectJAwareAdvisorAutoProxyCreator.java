package top.zzk0.aop;

import org.aopalliance.intercept.MethodInterceptor;
import top.zzk0.aop.aspectj.Advisor;
import top.zzk0.aop.aspectj.AspectJExpressionPointCutAdvisor;
import top.zzk0.aop.weave.AdvisedSupport;
import top.zzk0.aop.weave.JdkDynamicAopProxy;
import top.zzk0.aop.weave.TargetSource;
import top.zzk0.ioc.BeanPostProcessor;
import top.zzk0.ioc.factory.AbstractBeanFactory;
import top.zzk0.ioc.factory.BeanFactory;

import java.util.List;

/*
这个类集成了 BeanPostProcessor, 当进行 Bean 的初始化的时候，会调用里面的方法, 比如进行 weave
*/

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private AbstractBeanFactory abstractBeanFactory;

    @Override
    public Object postProcessBeforeInitialization(String name, Object bean) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String name, Object bean) throws Exception {
        List<String> advisors = abstractBeanFactory.getAdvisor();
        for (String advisorName : advisors) {
            AspectJExpressionPointCutAdvisor advisor =
                    (AspectJExpressionPointCutAdvisor)abstractBeanFactory.getBean(advisorName);
            if (advisor.getPointCut().getClassFilter().matches(bean.getClass())) {
                TargetSource targetSource =
                        new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                AdvisedSupport advisedSupport = new AdvisedSupport();
                advisedSupport.setTargetSource(targetSource);
                advisedSupport.setMethodInterceptor((MethodInterceptor)advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointCut().getMethodMatcher());
                JdkDynamicAopProxy proxy = new JdkDynamicAopProxy(advisedSupport);

                return proxy.getProxy();
            }
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        abstractBeanFactory = (AbstractBeanFactory)beanFactory;
    }
}
