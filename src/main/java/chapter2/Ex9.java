package chapter2;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class Ex9 {
    public static <T> ArrayList<T> join_r1(Stream<ArrayList<T>> streamOfLists) {
        Optional<ArrayList<T>> reduced = streamOfLists.reduce(Ex9::appendLists);
        return reduced.orElse(new ArrayList<T>());
    }

    public static <T> ArrayList<T> join_r2(Stream<ArrayList<T>> streamOfLists) {
        return streamOfLists.reduce(new ArrayList<>(), Ex9::appendLists);
    }

    public static <T> ArrayList<T> join_r3(Stream<ArrayList<T>> streamOfLists) {
        //this variant looks silly because the required return result of this reduction of Stream<T> is T
        //this form is however more powerful than that, and can reduce to another type as well
        return streamOfLists.reduce(
                new ArrayList<>(),
                Ex9::appendLists,
                Ex9::appendLists
        );
    }

    private static <T> ArrayList<T> appendLists(ArrayList<T> acc1, ArrayList<T> acc2) {
        ArrayList<T> result = new ArrayList<>();
        result.addAll(acc1);
        result.addAll(acc2);
        return result;
    }
}
