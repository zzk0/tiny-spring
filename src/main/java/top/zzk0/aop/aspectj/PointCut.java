package top.zzk0.aop.aspectj;

public interface PointCut {

    ClassFilter getClassFilter();
    MethodMatcher getMethodMatcher();

}
