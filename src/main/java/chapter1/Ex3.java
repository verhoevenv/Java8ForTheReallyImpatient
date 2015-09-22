package chapter1;

import java.io.File;

public class Ex3 {
    public String[] withExtension(File directory, String extension) {
        return directory.list((dir, name) -> name.endsWith("." + extension));
        // the "extension" variable is captured from the enclosing scope
    }
}
