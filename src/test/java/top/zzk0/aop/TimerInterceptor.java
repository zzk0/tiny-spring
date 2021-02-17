package top.zzk0.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long time = System.nanoTime();
        System.out.println("[开始调用] 方法名: " + methodInvocation.getMethod().getName());
        Object proceed =methodInvocation.proceed();
        Thread.sleep(123);
        System.out.println("[结束调用] 用时: " + (System.nanoTime() - time) / 1000000 + " ms");
        return proceed;
    }
}
