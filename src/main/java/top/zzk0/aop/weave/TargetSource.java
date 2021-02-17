package top.zzk0.aop.weave;

public class TargetSource {

    private Class targetClass;
    private Object target;

    public TargetSource(Class targetClass, Object target) {
        this.targetClass = targetClass;
        this.target = target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }
}
