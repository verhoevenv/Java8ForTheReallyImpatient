package chapter4;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Ex2Test {

    @Test
    public void propertyAccessWorksAsItShould() {
        Ex2 ex2 = new Ex2();
        StringProperty testProp = new SimpleStringProperty();

        StringProperty nameProperty = ex2.nameProperty();

        nameProperty.bindBidirectional(testProp);

        testProp.setValue("Walter White");
        Assertions.assertThat(ex2.getName()).isEqualTo("Walter White");

        ex2.setName("Jesse Pinkman");
        Assertions.assertThat(testProp.getValue()).isEqualTo("Jesse Pinkman");
    }

    @Test
    public void initiallyNoPropertyIsMade() {
        Ex2 ex2 = new Ex2();
        Assertions.assertThat(ex2.property_accessForTesting()).isNull();
    }

    @Test
    public void noPropertyIsMadeWhenAccessViaGetAndSet() {
        Ex2 ex2 = new Ex2();
        Assertions.assertThat(ex2.getName()).isNotEqualTo("Charlie Chaplin");
        ex2.setName("Charlie Chaplin");
        Assertions.assertThat(ex2.getName()).isEqualTo("Charlie Chaplin");

        Assertions.assertThat(ex2.property_accessForTesting()).isNull();
    }


    @Test
    public void propertyAccessWorksAsItShould_forEncapsulatedVersion() {
        Ex2_NowFeaturingEncapsulation ex2 = new Ex2_NowFeaturingEncapsulation();
        StringProperty testProp = new SimpleStringProperty();

        StringProperty nameProperty = ex2.nameProperty();

        nameProperty.bindBidirectional(testProp);

        testProp.setValue("Walter White");
        Assertions.assertThat(ex2.getName()).isEqualTo("Walter White");

        ex2.setName("Jesse Pinkman");
        Assertions.assertThat(testProp.getValue()).isEqualTo("Jesse Pinkman");
    }
}