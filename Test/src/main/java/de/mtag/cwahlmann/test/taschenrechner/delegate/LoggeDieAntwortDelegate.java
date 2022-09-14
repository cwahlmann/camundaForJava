package de.mtag.cwahlmann.test.taschenrechner.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggeDieAntwortDelegate implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(LoggeDieAntwortDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("*********************** Die Antwort auf die Frgae nach dem Leben, dem Universum und dem ganzen Rest lautet 42.");
    }
}
