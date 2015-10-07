package chapter4.ex8;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class EmployeeRole {
    private Property<Person> person = new SimpleObjectProperty<>();

    public Property<Person> personProperty() {
        return person;
    }
    public Person getPerson() {
        return person.getValue();
    }
    public void setPerson(Person newValue) {
        person.setValue(newValue);
    }

    public void walk(int managerLevel, BiConsumer<Integer, Person> personVisitor) {
        personVisitor.accept(managerLevel, person.getValue());
    }
}
