package chapter1;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

public class Ex4Test {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private Ex4 ex4;

    @Before
    public void createSUT() {
        ex4 = new Ex4();
    }

    @Test
    public void testSort() throws Exception {
        File folder2 = folder.newFolder("xyzzy");
        File file1 = folder.newFile("helloworld");
        File folder1 = folder.newFolder("abc");
        File file2 = folder.newFile("lamdasarecool");
        File[] files = {folder2, file1, folder1, file2};

        ex4.sortTypeThenNames(files);

        Assertions.assertThat(files).containsExactly(folder1, folder2, file1, file2);
    }

    @Test
    public void testAlternative() throws Exception {
        File folder2 = folder.newFolder("xyzzy");
        File file1 = folder.newFile("helloworld");
        File folder1 = folder.newFolder("abc");
        File file2 = folder.newFile("lamdasarecool");
        File[] files = {folder2, file1, folder1, file2};

        ex4.alternatively(files);

        Assertions.assertThat(files).containsExactly(folder1, folder2, file1, file2);
    }

}