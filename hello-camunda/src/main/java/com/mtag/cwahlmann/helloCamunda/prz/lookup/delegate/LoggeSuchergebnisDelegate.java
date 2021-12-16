package com.mtag.cwahlmann.helloCamunda.prz.lookup.delegate;

import com.mtag.cwahlmann.helloCamunda.prz.lookup.LookupConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoggeSuchergebnisDelegate implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(LoggeSuchergebnisDelegate.class);

    @Override
    @SuppressWarnings("unchecked")
    public void execute(DelegateExecution execution) throws Exception {
        var suchergebnis = (List<String>) execution.getVariable(LookupConstants.VAR_SUCHERGEBNIS);
        for (String address : suchergebnis) {
            log.info("******* {}: {}", execution.getProcessBusinessKey(), address);
        }
    }
}
