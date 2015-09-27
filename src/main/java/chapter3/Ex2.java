package chapter3;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

public class Ex2 {

    public interface RunnableEx {
        void run() throws Exception;
    }


    public static void withLock(ReentrantLock lock, RunnableEx r) throws Exception {
        lock.lock();
        try {
            r.run();
        } finally {
            lock.unlock();
        }
    }
}
