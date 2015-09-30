package chapter2;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Ex8 {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> itFirst = first.iterator();
        Iterator<T> itSecond = second.iterator();

        Iterator<T> zippedIterator = new ZippedIterator<>(itFirst, itSecond);
        return asStream(zippedIterator);
    }

    private static class ZippedIterator<T> implements Iterator<T> {
        private final Iterator<T> itFirst;
        private final Iterator<T> itSecond;
        boolean firstsTurn;

        public ZippedIterator(Iterator<T> itFirst, Iterator<T> itSecond) {
            this.itFirst = itFirst;
            this.itSecond = itSecond;
            firstsTurn = true;
        }

        @Override
        public boolean hasNext() {
            return !firstsTurn || (itFirst.hasNext() && itSecond.hasNext());
        }

        @Override
        public T next() {
            T nextVal = firstsTurn ? itFirst.next() : itSecond.next();
            firstsTurn = !firstsTurn;
            return nextVal;
        }
    }

    //from http://stackoverflow.com/questions/24511052/java8-iterator-to-stream
    private static <T> Stream<T> asStream(Iterator<T> sourceIterator) {
        Iterable<T> iterable = () -> sourceIterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
