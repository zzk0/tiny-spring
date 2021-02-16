package top.zzk0.xml;

import java.io.IOException;

/*
[interface] BeanDefinitionReader: 读取 bean 定义的接口
         |
         V
[Abstract] AbstractBeanDefinitionReader: 初始化数据结构, 通过使用 Abstract 避免 ResourceLoader 和具体实现耦合
         |
         V
XmlBeanDefinitionReader: 需要一个 InputStream 来读取配置, 然后 parse, 将类定义放入到父类的数据结构中
*/

public interface BeanDefinitionReader {

    void loadBeanDefinition(String location) throws Exception;
}
