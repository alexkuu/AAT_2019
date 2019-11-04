package service.listeners;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class JunitRunner extends BlockJUnit4ClassRunner {

    public JunitRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override public void run(RunNotifier notifier){
        notifier.addListener(new JUnitExecutionListener());
        notifier.fireTestRunStarted(getDescription());
        super.run(notifier);
    }
}
