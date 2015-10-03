package chapter4;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ex2 {
    //Don't do this in production code please
    private String nameValue;
    private StringProperty nameProperty;

    public String getName() {
        return nameProperty == null ? nameValue : nameProperty.getValue();
    }

    public void setName(String name) {
        if(nameProperty == null) {
            nameValue = name;
        } else {
            nameProperty.setValue(name);
        }
    }

    public StringProperty nameProperty() {
        if(nameProperty == null) {
            nameProperty = new SimpleStringProperty(nameValue);
        }
        return nameProperty;
    }

    protected StringProperty property_accessForTesting() {
        return nameProperty;
    }
}
