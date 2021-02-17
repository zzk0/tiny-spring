package top.zzk0.aop.weave;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInterceptor interceptor = advisedSupport.getMethodInterceptor();
        if (interceptor != null &&
                advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTargetClass())) {
            return interceptor.invoke(
                    new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args)
            );
        }
        return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(
                advisedSupport.getClass().getClassLoader(),
                new Class[]{advisedSupport.getTargetSource().getTargetClass()},
                this);
    }
}