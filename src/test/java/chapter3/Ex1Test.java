package chapter3;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex1Test {

    private Ex1.ConditionalLogger conditionalLogger;
    private LoggerStub loggerDependency;

    @Before
    public void setup() {
        loggerDependency = new LoggerStub();
        conditionalLogger = new Ex1.ConditionalLogger(loggerDependency);
    }

    @Test
    public void testLogIf_WhenConditionTrue_ThenLogMessage() {
        loggerDependency.setLevel(Level.INFO);

        conditionalLogger.logIf(Level.INFO, () -> true, () -> "Log this");

        Assertions.assertThat(loggerDependency.getMessages()).containsExactly("Log this");
    }

    @Test
    public void testLogIf_WhenCnditionFalse_ThenDontLogMessage() {
        loggerDependency.setLevel(Level.INFO);

        conditionalLogger.logIf(Level.INFO, () -> false, () -> "Log this");

        Assertions.assertThat(loggerDependency.getMessages()).isEmpty();
    }

    @Test
    public void testLogIf_WhenLevelIgnored_ThenDontEvaluateCondition_AndDontLogMessage() {
        loggerDependency.setLevel(Level.WARNING);

        conditionalLogger.logIf(Level.INFO,
                () -> {
                    Assertions.fail("Should not evaluate condition");
                    return false;
                },
                () -> "Log this");

        Assertions.assertThat(loggerDependency.getMessages()).isEmpty();
    }

    public class LoggerStub extends Logger {

        protected LoggerStub() {
            super(null, null);
        }

        private List<String> messages = new ArrayList<>();

        @Override
        public void log(Level level, Supplier<String> msgSupplier) {
            if(!isLoggable(level)) return;

            messages.add(msgSupplier.get());
        }

        public List<String> getMessages() {
            return messages;
        }
    }

}
