package top.zzk0.ioc;

public class BeanDefinition {

    private Object bean;
    private String className;
    private Class beanClass;
    private PropertyValues propertyValues;

    public BeanDefinition() {
        propertyValues = new PropertyValues();
    }

    public void setClassName(String className) {
        this.className = className;
        try {
            beanClass = Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------- 由 IDEA 生成 ----------------------------

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getClassName() {
        return className;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
