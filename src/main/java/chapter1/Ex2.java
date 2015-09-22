package chapter1;

import java.io.File;
import java.io.FilenameFilter;

public class Ex2 {
    public File[] subdirs_lambda(File directory) {
        return directory.listFiles(file -> file.isDirectory());
    }

    public File[] subdirs_method(File directory) {
        return directory.listFiles(File::isDirectory);
    }
}
