package chapter4;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Ex3Test {

    @Test
    public void propertyAccessWorksAsItShould_ohYeahThoseMightHappenToBeTheNamesOfMyCats() {
        Ex3 ex3 = new Ex3();
        StringProperty testProp = new SimpleStringProperty();

        StringProperty nameProperty = ex3.nameProperty();

        nameProperty.bindBidirectional(testProp);

        testProp.setValue("Nelson");
        Assertions.assertThat(ex3.getName()).isEqualTo("Nelson");

        ex3.setName("Nestor");
        Assertions.assertThat(testProp.getValue()).isEqualTo("Nestor");
    }

    @Test
    public void initiallyNoPropertyIsMade() {
        Ex3 ex3 = new Ex3();
        Assertions.assertThat(ex3.property_accessForTesting()).isNull();
    }

    @Test
    public void noPropertyIsMadeWhenAccessViaGet() {
        Ex3 ex3 = new Ex3();
        Assertions.assertThat(ex3.getName()).isEqualTo(Ex3.DEFAULT_NAME);
        Assertions.assertThat(ex3.property_accessForTesting()).isNull();
    }

    @Test
    public void whenValueIsSet_thenPropertyIsCreated() {
        Ex3 ex3 = new Ex3();
        ex3.setName("The Property Which Should Not Be Named");
        Assertions.assertThat(ex3.property_accessForTesting().getValue()).isEqualTo("The Property Which Should Not Be Named");
    }

}