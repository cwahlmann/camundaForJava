package com.mtag.cwahlmann.helloCamunda.prz.calculator2.delegate;

import com.mtag.cwahlmann.helloCamunda.prz.calculator2.Calculator2ProcessConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggeEingabenDelegate2 implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(LoggeEingabenDelegate2.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        var x = (Long) delegateExecution.getVariable(Calculator2ProcessConstants.VAR_X);
        var y = (Long) delegateExecution.getVariable(Calculator2ProcessConstants.VAR_Y);
        var operation = (String) delegateExecution.getVariable(Calculator2ProcessConstants.VAR_OPERATION);
        log.info("******* Input: {} {} {}", x, operation, y);
    }
}
