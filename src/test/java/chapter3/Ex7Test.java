package chapter3;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;

public class Ex7Test {

    @Test
    public void testNormalComparator() {
        Comparator<String> result = Ex7.createComparator(Ex7.Direction.NORMAL, Ex7.Casing.SENSITIVE, Ex7.Spacing.SENSITIVE);

        List<String> list = asList("ab", "c  d", "c b", "b", "Ac");
        list.sort(result);

        Assertions.assertThat(list).containsExactly("Ac", "ab", "b", "c  d", "c b");
    }

    @Test
    public void testReversedComparator() {
        Comparator<String> result = Ex7.createComparator(Ex7.Direction.REVERSED, Ex7.Casing.SENSITIVE, Ex7.Spacing.SENSITIVE);

        List<String> list = asList("ab", "c  d", "c b", "b", "Ac");
        list.sort(result);

        Assertions.assertThat(list).containsExactly("c b", "c  d", "b", "ab", "Ac");
    }

    @Test
    public void testCaseInsensitiveComparator() {
        Comparator<String> result = Ex7.createComparator(Ex7.Direction.NORMAL, Ex7.Casing.INSENSITIVE, Ex7.Spacing.SENSITIVE);

        List<String> list = asList("ab", "c  d", "c b", "b", "Ac");
        list.sort(result);

        Assertions.assertThat(list).containsExactly("ab", "Ac", "b", "c  d", "c b");
    }

    @Test
    public void testSpaceInsensitiveComparator() {
        Comparator<String> result = Ex7.createComparator(Ex7.Direction.NORMAL, Ex7.Casing.SENSITIVE, Ex7.Spacing.INSENSITIVE);

        List<String> list = asList("ab", "c  d", "c b", "b", "Ac");
        list.sort(result);

        Assertions.assertThat(list).containsExactly("Ac", "ab", "b", "c b", "c  d");
    }
}
