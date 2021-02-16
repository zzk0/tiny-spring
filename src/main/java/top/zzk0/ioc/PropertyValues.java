package top.zzk0.ioc;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    List<PropertyValue> propertyValueList;

    public PropertyValues() {
        propertyValueList = new ArrayList<PropertyValue>();
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
