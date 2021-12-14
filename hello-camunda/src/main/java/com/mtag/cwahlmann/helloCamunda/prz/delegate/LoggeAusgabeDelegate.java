package com.mtag.cwahlmann.helloCamunda.prz.delegate;

import com.mtag.cwahlmann.helloCamunda.prz.AccumulatorProcessConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggeAusgabeDelegate implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(LoggeAusgabeDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) {
        var z = (Integer) delegateExecution.getVariable(AccumulatorProcessConstants.VAR_Z);
        log.info("******* Output: z=" + z);
    }
}
