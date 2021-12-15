package com.mtag.cwahlmann.helloCamunda.prz.calculator.delegate;

import com.mtag.cwahlmann.helloCamunda.prz.calculator.CalculatorProcessConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;


@Component
public class BerechneErgebnisDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        var x = (Long) delegateExecution.getVariable(CalculatorProcessConstants.VAR_X);
        var y = (Long) delegateExecution.getVariable(CalculatorProcessConstants.VAR_Y);
        var operation = (String) delegateExecution.getVariable(CalculatorProcessConstants.VAR_OPERATION);
        var z = calculate(x, y, operation);
        delegateExecution.setVariable(CalculatorProcessConstants.VAR_Z, z);
    }

    private Long calculate(long x, long y, String operation) {
        switch (operation) {
            case "PLUS":
                return x + y;
        }
        throw new RuntimeException("Unknown Operation: " + operation);
    }
}
