package top.zzk0.factory;

import top.zzk0.ioc.BeanDefinition;
import top.zzk0.ioc.BeanReference;
import top.zzk0.ioc.PropertyValue;

import java.lang.reflect.Field;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object doCreateBean(BeanDefinition definition) throws Exception {
        Class beanClass = definition.getBeanClass();
        Object bean = beanClass.newInstance();

        // 创建之后就放入 definition 中, 后面 apply 的时候, 可能循环依赖于这个类, 届时将会读取到这个 bean
        definition.setBean(bean);

        applyPropertyValues(definition, bean);
        return bean;
    }

    /**
     * 初始化 bean 的属性
     * @param definition 定义
     * @param bean 实体
     * @throws Exception 异常随便抛...
     */
    private void applyPropertyValues(BeanDefinition definition, Object bean) throws Exception {
        for (PropertyValue propertyValue : definition.getPropertyValues().getPropertyValueList()) {
            Field field = bean.getClass().getDeclaredField(propertyValue.getField());
            field.setAccessible(true);
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference reference = (BeanReference)value;
                value = getBean(reference.getName());
            }
            field.set(bean, value);
        }
    }
}
