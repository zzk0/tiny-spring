package top.zzk0.aop.aspectj;

import java.lang.reflect.Method;

public interface MethodMatcher {

    boolean matches(Method method, Class targetClass);
}
