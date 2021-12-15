package com.mtag.cwahlmann.helloCamunda.prz.calculator.delegate;

import com.mtag.cwahlmann.helloCamunda.prz.calculator.CalculatorProcessConstants;
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
        var x = (Long) delegateExecution.getVariable(CalculatorProcessConstants.VAR_X);
        var y = (Long) delegateExecution.getVariable(CalculatorProcessConstants.VAR_Y);
        var operation = (String) delegateExecution.getVariable(CalculatorProcessConstants.VAR_OPERATION);
        log.info("******* Input: {} {} {}", x, operation, y);
    }
}
