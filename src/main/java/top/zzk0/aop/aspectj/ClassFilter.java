package top.zzk0.aop.aspectj;

public interface ClassFilter {

    boolean matches(Class targetClass);
}
