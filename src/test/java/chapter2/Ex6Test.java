package chapter2;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class Ex6Test {

    @Test
    public void testCharacterStream() throws Exception {
        Stream<Character> result = Ex6.characterStream("ABC");

        Assertions.assertThat(result.toArray()).containsExactly('A', 'B', 'C');
    }
}