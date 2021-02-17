package top.zzk0.aop.weave;

import org.aopalliance.intercept.MethodInterceptor;
import top.zzk0.aop.aspectj.MethodMatcher;

public class AdvisedSupport {

    private TargetSource targetSource;
    private MethodInterceptor methodInterceptor;
    private MethodMatcher methodMatcher;

    // ---------------------------- 由 IDEA 生成 ----------------------------

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
