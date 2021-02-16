package top.zzk0.factory;

import top.zzk0.ioc.BeanDefinition;

/*
[interface] BeanFactory: 提供基本的接口, getBean, register
         |
         V
[Abstract] AbstractBeanFactory: 实现上面的接口, 把创建 bean 的 doCreateBean 抽象出来
         |
         V
AutowireCapableBeanFactory: 实现 doCreateBean, 根据 register 中的信息实例化, 设置类成员
*/

public interface BeanFactory {

    /**
     * 获取名字为 name 的 Bean
     * @param name 名字
     * @return Bean
     */
    Object getBean(String name) throws Exception;

    /**
     * 注册一个 definition
     * @param name 名字
     * @param definition 里面包含类名用于初始化等
     */
    void register(String name, BeanDefinition definition) throws Exception;
}
