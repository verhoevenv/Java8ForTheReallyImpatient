package chapter4;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Ex5 {
    public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> t) {
        return new FunctionObservable<>(f, t);
    }

    public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u) {
        return new BiFunctionObservable<>(f, t, u);
    }

    private static class FunctionObservable<T, R> extends ObservableValueBase<R> implements InvalidationListener, ChangeListener<T> {

        private final Function<T, R> f;
        private final ObservableValue<T> t;

        public FunctionObservable(Function<T, R> f, ObservableValue<T> t) {
            this.f = f;
            this.t = t;
            t.addListener((InvalidationListener)this);
            t.addListener((ChangeListener<T>)this);
        }

        @Override
        public R getValue() {
            return f.apply(t.getValue());
        }

        @Override
        public void invalidated(Observable observable) {
            fireValueChangedEvent();
        }

        @Override
        public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
            fireValueChangedEvent();
        }
    }

    //Note how the "implements ChangeListener<T>" approach does not work here,
    //because we have to implement two different changelisteners.
    //So we use composition and lambdas. Ow yeah.
    private static class BiFunctionObservable<T, U, R> extends ObservableValueBase<R> {

        private final BiFunction<T, U, R> f;
        private final ObservableValue<T> t;
        private final ObservableValue<U> u;

        public BiFunctionObservable(BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u) {
            this.f = f;
            this.t = t;
            this.u = u;
            t.addListener(x -> this.fireValueChangedEvent());
            t.addListener((x, o, n) -> this.fireValueChangedEvent());
            u.addListener((x, o, n) -> this.fireValueChangedEvent());
            u.addListener((x, o, n) -> this.fireValueChangedEvent());
        }

        @Override
        public R getValue() {
            return f.apply(t.getValue(), u.getValue());
        }
    }
}
