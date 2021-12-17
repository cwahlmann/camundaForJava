package com.mtag.cwahlmann.helloCamunda.prz.veriableScope;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogXDelegate implements JavaDelegate {
    public static final Logger log = LoggerFactory.getLogger(LogXDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var x = execution.getVariable("x");
        log.info("x = {}", x);
    }
}
