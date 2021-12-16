package com.mtag.cwahlmann.helloCamunda.prz.lookup.delegate;

import com.mtag.cwahlmann.helloCamunda.prz.lookup.LookupConstants;
import com.mtag.cwahlmann.helloCamunda.prz.lookup.LookupService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SetzeBusinesskeyDelegate implements JavaDelegate {
    private final LookupService lookupService;

    public SetzeBusinesskeyDelegate(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // set businesskey
        execution.setProcessBusinessKey(UUID.randomUUID().toString());
    }
}
