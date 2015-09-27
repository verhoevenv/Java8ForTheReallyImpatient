package chapter3;

@SuppressWarnings("ConstantConditions")
public class Ex3 {
    private static final Env SOME_GLOBAL_ENVIRONMENT_PROPERTY = Env.TEST;

    public static void main(String[] args) {
        //Setting up some things, I'll be right back with you...
        String[] manymanyString = giveMeABunchOfStrings();

        
        //Okay, ready! Suppose we have to do some assertion that takes a long time, say...
        assertAllStringAreNonEmpty(manymanyString);

        //We want to do these checks in our testing environment, but not in production, because it takes a long time.
        //We have to be performant!
        //(this is not always a concern, but it could be, right?)
        if(SOME_GLOBAL_ENVIRONMENT_PROPERTY == Env.TEST) {
            assertAllStringAreNonEmpty(manymanyString);
        }

        //If we want to extract a pattern out of this, we need deferred code execution...
        if(SOME_GLOBAL_ENVIRONMENT_PROPERTY == Env.TEST) {
            if(someUserCode()) {
                throw new AssertionError("Empty string, abandon your posts!");
            }
        }

        //The java 1.4 solution to this was to provide a keyword:
        assert someUserCode();

        //Another way would be with a library function that takes some kind of object that produces a boolean on request...
        AssertionLib.assert2(new ConditionLike() {
            @Override
            public boolean evaluate() {
                return isAnyStringEmpty(manymanyString);
            }
        });

        //But that gets clunky really fast and still creates an object that you will not need in production environment.

        //With java 8 however, it is a lot more elegant:
        AssertionLib.assert2(() -> isAnyStringEmpty(manymanyString));

        //So I guess it can be done as a library function now.
        //Note that the "ConditionLike" interface can be replaces with Supplier<Boolean> in java 8.
    }

    private static boolean isAnyStringEmpty(String[] mystrings) {
        for (String mystring : mystrings) {
            if(mystring.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static void assertAllStringAreNonEmpty(String[] mystrings) {
        if(isAnyStringEmpty(mystrings)) {
            throw new AssertionError("Empty string, abandon your posts!");
        }
    }

    private static String[] giveMeABunchOfStrings() {
        final int number_of_objects = 1000; //but it could be more!
        String[] strings = new String[number_of_objects];
        for (int i=0; i<number_of_objects; i++) {
            strings[i] = "Hello World";
        }
        return strings;
    }

    private enum Env {
        TEST, PRODUCTION;
    }

    private static boolean someUserCode() {
        //For demo purposes
        return false;
    }

    public interface ConditionLike {
        boolean evaluate();
    }

    public static class AssertionLib {
        public static void assert2(ConditionLike c) {
            if(c.evaluate()) {
                throw new AssertionError("Empty string, abandon your posts!");
            }
        }
    }
}
