package com.mtag.cwahlmann.helloCamunda.prz.calculator2.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggeDieAntwortDelegate implements JavaDelegate {
    private static Logger log = LoggerFactory.getLogger(LoggeDieAntwortDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("******* Die Antwort auf die Frage nach dem Leben, dem Universum und dem ganzen Rest ist zweiundvierzig.");
    }
}
