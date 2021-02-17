package top.zzk0.aop.aspectj;

public interface PointCutAdvisor extends Advisor {

    PointCut getPointCut();
}
