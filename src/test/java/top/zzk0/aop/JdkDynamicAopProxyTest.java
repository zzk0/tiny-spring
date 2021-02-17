package top.zzk0.aop;

import org.junit.jupiter.api.Test;
import top.zzk0.aop.weave.AdvisedSupport;
import top.zzk0.aop.weave.JdkDynamicAopProxy;
import top.zzk0.aop.weave.TargetSource;
import top.zzk0.bean.Animal;
import top.zzk0.bean.Bone;
import top.zzk0.bean.Dog;

class JdkDynamicAopProxyTest {

    @Test
    public void testAop() {
        // 正常调用
        Dog dog = new Dog();
        dog.setName("wangcat");
        Bone bone = new Bone();
        bone.setOwner(dog);
        bone.setType("pig");
        dog.say();

        // 动态代理
        TargetSource targetSource = new TargetSource(Animal.class, dog);
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(new TimerInterceptor());
        JdkDynamicAopProxy proxy = new JdkDynamicAopProxy(advisedSupport);

        // 获取代理对象
        Animal dog1 = (Animal)proxy.getProxy();
        dog1.say();
    }
}