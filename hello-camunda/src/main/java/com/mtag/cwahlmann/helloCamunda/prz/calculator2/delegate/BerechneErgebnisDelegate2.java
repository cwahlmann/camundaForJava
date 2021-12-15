package com.mtag.cwahlmann.helloCamunda.prz.calculator2.delegate;

import com.mtag.cwahlmann.helloCamunda.prz.calculator2.Calculator2ProcessConstants;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;


@Component
public class BerechneErgebnisDelegate2 implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        var x = (Long) delegateExecution.getVariable(Calculator2ProcessConstants.VAR_X);
        var y = (Long) delegateExecution.getVariable(Calculator2ProcessConstants.VAR_Y);
        var operation = (String) delegateExecution.getVariable(Calculator2ProcessConstants.VAR_OPERATION);
        var z = calculate(x, y, operation);
        delegateExecution.setVariable(Calculator2ProcessConstants.VAR_Z, z);
    }

    private Long calculate(long x, long y, String operation) {
        switch (operation) {
            case "PLUS":
                return x + y;
            case "MINUS":
                return x - y;
            case "MULTIPLY":
                return x * y;
            case "DIVIDE":
                if (y == 0) {
                    throw new BpmnError("1", "Division by zero is not defined.");
                }
                return x / y;
            case "SQUARE":
                return x * x;
            case "SQUARE_ROOT":
                if (x < 0) {
                    throw new BpmnError("2", "Square root not defined for negative values.");
                }
                return (long) Math.sqrt(x);
        }
        throw new BpmnError("99", "Unknown Operation: " + operation);
    }
}
