package chapter4;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class Ex5Test {

    @Test
    public void testObserve_function_correctValue() throws Exception {
        IntegerProperty i = new SimpleIntegerProperty();

        ObservableValue<Boolean> result = Ex5.observe(x -> x.intValue() > 100, i);

        i.setValue(20);
        assertThat(result.getValue()).isFalse();
        i.setValue(320);
        assertThat(result.getValue()).isTrue();
    }

    @Test
    public void testObserve_function_invalidationListener() throws Exception {
        IntegerProperty i = new SimpleIntegerProperty();

        ObservableValue<Boolean> result = Ex5.observe(x -> x.intValue() > 100, i);

        InvalidationListenerStub listenerStub = new InvalidationListenerStub();

        i.setValue(20);
        result.addListener(listenerStub);
        assertThat(listenerStub.hasBeenCalled()).isFalse();
        i.setValue(320);
        assertThat(listenerStub.hasBeenCalled()).isTrue();
    }

    @Test
    public void testObserve_function_changeListener() throws Exception {
        IntegerProperty i = new SimpleIntegerProperty();

        ObservableValue<Boolean> result = Ex5.observe(x -> x.intValue() > 100, i);

        ChangeListenerStub<Boolean> changeListenerStub = new ChangeListenerStub<>();

        i.setValue(20);
        result.addListener(changeListenerStub);
        assertThat(changeListenerStub.getOldValue()).isNull();
        assertThat(changeListenerStub.getNewValue()).isNull();
        i.setValue(320);
        assertThat(changeListenerStub.getOldValue()).isFalse();
        assertThat(changeListenerStub.getNewValue()).isTrue();
    }

    @Test
    public void testObserve_bifunction_correctValue() throws Exception {
        IntegerProperty i = new SimpleIntegerProperty();
        IntegerProperty j = new SimpleIntegerProperty();

        ObservableValue<Integer> result = Ex5.observe((x, y) -> x.intValue() + y.intValue(), i, j);

        i.setValue(20);
        j.setValue(100);
        assertThat(result.getValue()).isEqualTo(120);
        i.setValue(320);
        assertThat(result.getValue()).isEqualTo(420);
        j.setValue(-20);
        assertThat(result.getValue()).isEqualTo(300);
    }

    @Test
    public void testObserve_bifunction_invalidationListener_changeFirst() throws Exception {
        IntegerProperty i = new SimpleIntegerProperty();
        IntegerProperty j = new SimpleIntegerProperty();

        ObservableValue<Integer> result = Ex5.observe((x, y) -> x.intValue() + y.intValue(), i, j);

        InvalidationListenerStub listenerStub = new InvalidationListenerStub();

        i.setValue(20);
        j.setValue(100);
        result.addListener(listenerStub);
        assertThat(listenerStub.hasBeenCalled()).isFalse();
        i.setValue(320);
        assertThat(listenerStub.hasBeenCalled()).isTrue();
    }

    @Test
    public void testObserve_bifunction_invalidationListener_changeSecond() throws Exception {
        IntegerProperty i = new SimpleIntegerProperty();
        IntegerProperty j = new SimpleIntegerProperty();

        ObservableValue<Integer> result = Ex5.observe((x, y) -> x.intValue() + y.intValue(), i, j);

        InvalidationListenerStub listenerStub = new InvalidationListenerStub();

        i.setValue(20);
        j.setValue(100);
        result.addListener(listenerStub);
        assertThat(listenerStub.hasBeenCalled()).isFalse();
        j.setValue(320);
        assertThat(listenerStub.hasBeenCalled()).isTrue();
    }

    @Test
    public void testObserve_bifunction_changeListener() throws Exception {
        IntegerProperty i = new SimpleIntegerProperty();
        IntegerProperty j = new SimpleIntegerProperty();

        ObservableValue<Integer> result = Ex5.observe((x, y) -> x.intValue() + y.intValue(), i, j);

        ChangeListenerStub<Integer> changeListenerStub = new ChangeListenerStub<>();

        i.setValue(20);
        j.setValue(100);
        result.addListener(changeListenerStub);
        assertThat(changeListenerStub.getOldValue()).isNull();
        assertThat(changeListenerStub.getNewValue()).isNull();

        i.setValue(320);
        assertThat(changeListenerStub.getOldValue()).isEqualTo(120);
        assertThat(changeListenerStub.getNewValue()).isEqualTo(420);

        j.setValue(-20);
        assertThat(changeListenerStub.getOldValue()).isEqualTo(420);
        assertThat(changeListenerStub.getNewValue()).isEqualTo(300);
    }

    private static class InvalidationListenerStub implements InvalidationListener {
        private boolean hasBeenCalled = false;
        @Override
        public void invalidated(Observable observable) {
            hasBeenCalled = true;
        }

        public boolean hasBeenCalled() {
            return hasBeenCalled;
        }
    }


    private static class ChangeListenerStub<T> implements ChangeListener<T> {

        private T oldValue;
        private T newValue;

        @Override
        public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public T getOldValue() {
            return oldValue;
        }

        public T getNewValue() {
            return newValue;
        }
    }
}