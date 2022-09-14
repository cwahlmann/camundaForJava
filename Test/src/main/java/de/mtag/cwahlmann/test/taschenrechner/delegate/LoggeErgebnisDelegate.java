package de.mtag.cwahlmann.test.taschenrechner.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggeErgebnisDelegate implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(LoggeErgebnisDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var x = (Long) execution.getVariable("x");
        var y = (Long) execution.getVariable("y");
        var o = (String) execution.getVariable("o");
        var z = (Long) execution.getVariable("z");
        log.info("*********************** {} {} {} = {}", x, o, y, z);
    }
}
