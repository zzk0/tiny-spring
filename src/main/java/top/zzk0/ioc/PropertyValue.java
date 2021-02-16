package top.zzk0.ioc;

public class PropertyValue {

    private String field;
    private Object value;

    public PropertyValue() {}

    public PropertyValue(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    // ---------------------------- 由 IDEA 生成 ----------------------------

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
