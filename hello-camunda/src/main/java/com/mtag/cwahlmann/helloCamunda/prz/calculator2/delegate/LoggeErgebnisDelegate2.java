package com.mtag.cwahlmann.helloCamunda.prz.calculator2.delegate;

import com.mtag.cwahlmann.helloCamunda.prz.calculator2.Calculator2ProcessConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggeErgebnisDelegate2 implements JavaDelegate {
    private static Logger log = LoggerFactory.getLogger(LoggeErgebnisDelegate2.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        var x = (Long) delegateExecution.getVariable(Calculator2ProcessConstants.VAR_X);
        var y = (Long) delegateExecution.getVariable(Calculator2ProcessConstants.VAR_Y);
        var operation = (String) delegateExecution.getVariable(Calculator2ProcessConstants.VAR_OPERATION);
        var z = (Long) delegateExecution.getVariable(Calculator2ProcessConstants.VAR_Z);
        log.info("******* Output: {} {} {} = {}", x, operation, y, z);
    }
}
