package general;

public class Uncheck {
    public static Runnable uncheck(RunnableEx wrapped) {
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
}
