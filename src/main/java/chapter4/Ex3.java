package chapter4;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.function.Supplier;

import static chapter4.Ex3.DefaultProperty.defaultProperty;

public class Ex3 {

    public static final String DEFAULT_NAME = "My name is Daisy E. Fault";

    private DefaultProperty<String> name = defaultProperty(DEFAULT_NAME, SimpleStringProperty::new);

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public StringProperty nameProperty() {
        return (StringProperty) name.property();
    }

    protected StringProperty property_accessForTesting() {
        return (StringProperty) name.property;
    }

    //this could of course be extracted and reused
    public static class DefaultProperty<T> {
        private Property<T> property = null;

        private final T defaultVal;
        private final Supplier<Property<T>> propertyFactory;

        private DefaultProperty(T defaultVal, Supplier<Property<T>> propertyFactory) {
            this.defaultVal = defaultVal;
            this.propertyFactory = propertyFactory;
        }

        public static <T> DefaultProperty<T> defaultProperty(T defaultVal, Supplier<Property<T>> propertyFactory) {
            return new DefaultProperty<>(defaultVal, propertyFactory);
        }

        public T getValue() {
            return property == null ? defaultVal : property.getValue();
        }

        public void setValue(T value) {
            property().setValue(value);
        }

        public Property<T> property() {
            if (property == null) {
                property = propertyFactory.get();
                property.setValue(defaultVal);
            }
            return property;
        }
    }
}
