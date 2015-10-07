package chapter4.ex8;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Company {
    private StringProperty name = new SimpleStringProperty();
    private Property<ManagerRole> ceo = new SimpleObjectProperty<>();

    public final StringProperty nameProperty() { return name; }
    public final void setName(String newValue) { name.set(newValue); }
    public final String getName() { return name.get(); }

    public final Property<ManagerRole> ceoProperty() { return ceo; }
    public final void setCeo(ManagerRole newValue) { ceo.setValue(newValue); }
    public final ManagerRole getCeo() { return ceo.getValue(); }

    public void printOrganization() {
        walk((l, p) -> System.out.println(repeat(l, "  ") + p.getName()));
    }

    public void walk(BiConsumer<Integer, Person> personVisitor){
        getCeo().walk(0, personVisitor);
    }

    private static String repeat(int times, String s) {
        return Stream.generate(() -> s).limit(times).collect(Collectors.joining());
    }
}
