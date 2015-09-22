package chapter1;

public class Ex7 {

    public static Runnable andThen(Runnable r1, Runnable r2) {
        return () -> { r1.run(); r2.run();};
    }

    public static void main(String[] args) {
        Runnable printAndPrintSomeMore = andThen(
                () -> {System.out.println("Hello");},
                () -> {System.out.println("World");}
        );

        printAndPrintSomeMore.run();
    }
}
