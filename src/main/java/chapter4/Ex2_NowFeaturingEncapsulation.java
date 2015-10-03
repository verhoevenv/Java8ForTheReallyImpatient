package chapter4;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.function.Supplier;

import static chapter4.Ex2_NowFeaturingEncapsulation.LazyProperty.lazyProperty;

public class Ex2_NowFeaturingEncapsulation {

    private LazyProperty<String> name = lazyProperty(SimpleStringProperty::new);

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public StringProperty nameProperty() {
        return (StringProperty) name.property();
    }

    //this could of course be extracted and reused
    public static class LazyProperty<T> {
        private T value = null;
        private Property<T> property = null;
        private final Supplier<Property<T>> propertyFactory;

        private LazyProperty(Supplier<Property<T>> propertyFactory) {
            this.propertyFactory = propertyFactory;
        }

        public static <T> LazyProperty<T> lazyProperty(Supplier<Property<T>> propertyFactory) {
            return new LazyProperty<>(propertyFactory);
        }

        public T getValue() {
            return property == null ? value : property.getValue();
        }

        public void setValue(T value) {
            if (property == null) {
                this.value = value;
            } else {
                this.property.setValue(value);
            }
        }

        public Property<T> property() {
            if (property == null) {
                property = propertyFactory.get();
                property.setValue(value);
            }
            return property;
        }
    }
}
