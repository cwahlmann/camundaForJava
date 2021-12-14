package com.mtag.cwahlmann.helloCamunda.prz.delegate;

import com.mtag.cwahlmann.helloCamunda.prz.AccumulatorProcessConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;


@Component
public class AddiereEingabenDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        var x = (Integer) delegateExecution.getVariable(AccumulatorProcessConstants.VAR_X);
        var y = (Integer) delegateExecution.getVariable(AccumulatorProcessConstants.VAR_Y);
        var z = x + y;
        delegateExecution.setVariable(AccumulatorProcessConstants.VAR_Z, z);
    }
}
