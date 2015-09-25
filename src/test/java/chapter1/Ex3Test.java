package chapter1;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

public class Ex3Test {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private Ex3 ex3;

    @Before
    public void createSUT() {
        ex3 = new Ex3();
    }

    @Test
    public void testWithExtension() throws Exception {
        folder.newFile("file1.txt");
        folder.newFile("file2.bla");

        String[] files = ex3.withExtension(folder.getRoot(), "txt");

        Assertions.assertThat(files).containsExactly("file1.txt");
    }

}