package chapter3;

import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex1 {
    public static class ConditionalLogger {

        private Logger logger;

        public ConditionalLogger(Logger logger) {
            this.logger = logger;
        }

        public void logIf(Level level, Supplier<Boolean> condition, Supplier<String> message) {
            if(logger.isLoggable(level) && condition.get()) {
               logger.log(level, message);
            }
        }
    }

    public ConditionalLogger getGlobalConditional() {
        return new ConditionalLogger(Logger.getGlobal());
    }
}
