package chapter4.ex8;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class ManagerRole extends EmployeeRole {

    ObservableList<EmployeeRole> employees = FXCollections.observableArrayList(new ArrayList<>());
    private ListProperty<EmployeeRole> employeesProperty = new SimpleListProperty<>(employees);

    public ListProperty<EmployeeRole> employeesProperty() {
        return employeesProperty;
    }
    public ObservableList<EmployeeRole> getEmployees() {
        return employeesProperty.get();
    }

    public void walk(int managerLevel, BiConsumer<Integer, Person> personVisitor) {
        super.walk(managerLevel, personVisitor);
        employees.forEach(e -> e.walk(managerLevel + 1, personVisitor));
    }
}
