package chapter3;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;

public class Ex9Test {


    public class Name {
        public final String firstname;
        public final String lastname;


        public Name(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        @Override
        public String toString() {
            return String.format("%s %s", firstname, lastname);
        }
    }

    @Test
    public void testLexographicComparator() {
        Comparator<Name> lastThenFirstComparator = Ex9.lexographicComparator("lastname", "firstname");
        Comparator<Name> firstThenLastComparator = Ex9.lexographicComparator("firstname", "lastname");

        Name n1 = new Name("Charles", "Michel");
        Name n2 = new Name("Elio", "Di Rupo");
        Name n3 = new Name("Yves", "Leterme");
        Name n4 = new Name("Herman", "Van Rompuy");
        Name n5 = new Name("Guy", "Verhofstadt");

        Name[] namesByLastname = new Name[] {n1, n2, n3, n4, n5};
        Arrays.sort(namesByLastname, lastThenFirstComparator);
        Name[] namesByFirstname = new Name[] {n1, n2, n3, n4, n5};
        Arrays.sort(namesByFirstname, firstThenLastComparator);

        Assertions.assertThat(namesByLastname).containsExactly(n2, n3, n1, n4, n5);
        Assertions.assertThat(namesByFirstname).containsExactly(n1, n2, n5, n4, n3);
    }
}