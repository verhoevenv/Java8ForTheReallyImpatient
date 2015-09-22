package chapter1;

import java.util.ArrayList;
import java.util.List;

public class Ex8 {

    public static List<Runnable> withEnhancedLoop() {
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();
        for (String name : names) {
            runners.add(() -> System.out.println(name));
        }
        return runners;
    }

    public static List<Runnable> withOldSchoolLoop() {
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            //note what happens when you inline the "name" variable, which makes the "i" get referenced inside the lambda
            String name = names[i];
            runners.add(() -> {
                System.out.println(name);
            });
        }
        return runners;
    }

    public static void main(String[] args) {
        run(withEnhancedLoop());
        run(withOldSchoolLoop());
    }

    public static void run(List<Runnable> runners) {
        runners.forEach(Runnable::run);
    }
}
