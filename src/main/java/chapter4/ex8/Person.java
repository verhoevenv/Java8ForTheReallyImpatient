package chapter4.ex8;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private StringProperty name = new SimpleStringProperty();

    public final StringProperty nameProperty() { return name; }
    public final void setName(String newValue) { name.set(newValue); }
    public final String getName() { return name.get(); }
}
