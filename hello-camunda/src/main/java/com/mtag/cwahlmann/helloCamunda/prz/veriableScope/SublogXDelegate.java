package com.mtag.cwahlmann.helloCamunda.prz.veriableScope;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SublogXDelegate implements JavaDelegate {
    public static final Logger log = LoggerFactory.getLogger(SublogXDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var x = execution.getVariable("x");
        var x1 = execution.getSuperExecution().getVariable("x");
        log.info("x = {}, x1 = {}", x, x1);
    }
}
