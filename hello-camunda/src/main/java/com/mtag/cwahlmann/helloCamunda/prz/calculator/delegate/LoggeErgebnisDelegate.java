package com.mtag.cwahlmann.helloCamunda.prz.calculator.delegate;

import com.mtag.cwahlmann.helloCamunda.prz.calculator.CalculatorProcessConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggeErgebnisDelegate implements JavaDelegate {
    private static Logger log = LoggerFactory.getLogger(LoggeErgebnisDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        var x = (Long) delegateExecution.getVariable(CalculatorProcessConstants.VAR_X);
        var y = (Long) delegateExecution.getVariable(CalculatorProcessConstants.VAR_Y);
        var operation = (String) delegateExecution.getVariable(CalculatorProcessConstants.VAR_OPERATION);
        var z = (Long) delegateExecution.getVariable(CalculatorProcessConstants.VAR_Z);
        log.info("******* Output: {} {} {} = {}", x, operation, y, z);
    }
}
