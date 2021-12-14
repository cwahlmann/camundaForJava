package com.mtag.cwahlmann.helloCamunda.prz.delegate;

import com.mtag.cwahlmann.helloCamunda.prz.AccumulatorProcessConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggeEingabenDelegate implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(LoggeEingabenDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        var x = (Integer) delegateExecution.getVariable(AccumulatorProcessConstants.VAR_X);
        var y = (Integer) delegateExecution.getVariable(AccumulatorProcessConstants.VAR_Y);
        log.info("******* Input: x=" + x + ", y=" + y);
    }
}
