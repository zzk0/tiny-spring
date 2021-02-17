package top.zzk0.aop.weave;

/*
TargetSource: 存储对象及其类结构信息
AdvisedSupport: 存储一个 TargetSource, 一个 MethodInterceptor, Interceptor 截取方法
JdkDynamicAopProxy: 动态代理, 实现 AopProxy 的接口 getProxy 获取代理对象
ReflectiveMethodInvocation: 函数调用的信息, 包括对象、方法、参数

依赖关系:
JdkDynamicAopProxy(调用 getProxy 来获取代理对象)
--> AdvisedSupport(传入 interceptor)
--> TargetSource(传入对象和类)
*/

public interface AopProxy {
    Object getProxy();
}
