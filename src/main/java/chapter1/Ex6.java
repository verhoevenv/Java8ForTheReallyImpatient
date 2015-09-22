package chapter1;

import java.util.concurrent.Callable;

public class Ex6 {
    public static void main(String[] args) {
        new Thread(uncheck(
                () -> {
                    System.out.println("Zzz");
                    Thread.sleep(1000);
                })).start();
    }

    private static Runnable uncheck(RunnableEx wrapped) {
        return () -> {
            try {
                wrapped.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @FunctionalInterface
    public interface RunnableEx {
        void run() throws Exception;
    }



    //The following won't compile because Callable will expect a return result, even if you specialize it to Void
    //It's a Java thing...

    /*
    public static void mainThatWontCompileAnyway(String[] args) {
        new Thread(uncheckCallable(
                () -> { System.out.println("Zzz"); Thread.sleep(1000); })).start();
    }

    private static Runnable uncheckCallable(Callable<Void> wrapped) {
        return () -> {
            try {
                wrapped.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    */
}
