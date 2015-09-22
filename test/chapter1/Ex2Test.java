package chapter1;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

public class Ex2Test {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private Ex2 ex2;

    @Before
    public void createSUT() {
        ex2 = new Ex2();
    }

    @Test
    public void testSubdirs_lamdba() throws Exception {
        File subfolder = folder.newFolder("subfolder");
        folder.newFile("newFile");

        File[] subdirs = ex2.subdirs_lambda(folder.getRoot());

        Assertions.assertThat(subdirs).containsExactly(subfolder);
    }
    
    @Test
    public void testSubdirs_method() throws Exception {
        File subfolder = folder.newFolder("subfolder");
        folder.newFile("newFile");

        File[] subdirs = ex2.subdirs_method(folder.getRoot());

        Assertions.assertThat(subdirs).containsExactly(subfolder);
    }
}