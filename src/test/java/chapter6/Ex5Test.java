package chapter6;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;
import static org.junit.Assert.*;

public class Ex5Test {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void testOccurrences() throws Exception {
        File file1 = createTempFileWIthContents("this is a line of the first file");
        File file2 = createTempFileWIthContents("this is a line of the second file");
        Set<File> files = asSet(file1, file2);

        Map<String, Set<File>> occurrences = Ex5.occurrences(files);

        assertThat(occurrences).contains(
                entry("this", asSet(file1, file2)),
                entry("file", asSet(file1, file2)),
                entry("first", singleton(file1)),
                entry("second", singleton(file2))
        );
    }

    private File createTempFileWIthContents(String o) throws IOException {
        File file1 = temp.newFile();
        List<String> file1lines = singletonList(o);
        Files.write(file1.toPath(), file1lines);
        return file1;
    }

    @SafeVarargs
    private final <T> Set<T> asSet(T... objs) {
        HashSet<T> ts = new HashSet<>();
        Collections.addAll(ts, objs);
        return ts;
    }


}