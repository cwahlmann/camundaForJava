package com.mtag.cwahlmann.helloCamunda.prz.hello.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloDelegate implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(HelloDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("******* HELLO CAMUNDA *******");
    }
}
