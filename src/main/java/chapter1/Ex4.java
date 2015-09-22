package chapter1;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Ex4 {
    public void sortTypeThenNames(File[] files) {
        Arrays.sort(files, (f1, f2) -> {
            int onTypes = Boolean.compare(f1.isFile(), f2.isFile());
            if(onTypes != 0) return onTypes;

            return f1.getName().compareTo(f2.getName());
        });
    }

    public void alternatively(File[] files) {
        Arrays.sort(files, Comparator.comparing(File::isFile).thenComparing(File::getName));
    }
}
