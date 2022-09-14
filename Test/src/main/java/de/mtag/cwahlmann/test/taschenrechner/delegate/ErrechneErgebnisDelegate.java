package de.mtag.cwahlmann.test.taschenrechner.delegate;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.OperationNotSupportedException;
import java.util.List;

@Component
public class ErrechneErgebnisDelegate implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(ErrechneErgebnisDelegate.class);

    private final List<String> irgendwas;

    @Autowired
    public ErrechneErgebnisDelegate(List<String> irgendwas) {
        this.irgendwas = irgendwas;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var x = (Long) execution.getVariable("x");
        var y = (Long) execution.getVariable("y");
        var o = (String) execution.getVariable("o");
        long z;
        switch (o) {
            case "PLUS":
                z = x + y;
                break;
            case "DIVIDE":
                if (y == 0) {
                    throw new BpmnError("1", "Division by Zero Error");
                }
                z = x / y;
                break;
            default:
                throw new BpmnError("2", "Operation wird nicht unterstützt: " + o);
        }
        execution.setVariable("z", z);
    }
}
