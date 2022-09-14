package de.mtag.cwahlmann.test.delegate;

import de.mtag.cwahlmann.test.HalloProzessContants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.rmi.server.ServerNotActiveException;

@Component("sageHalloBean")
public class HalloDelegate implements JavaDelegate {
    public static final Logger log = LoggerFactory.getLogger(HalloDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var nachricht = (String) execution.getVariable(HalloProzessContants.VAR_NACHRICHT);
        log.info("************** {} **************", nachricht);

        execution.setVariable(HalloProzessContants.VAR_NACHRICHT, "Du Da");

        throw new RuntimeException("FEHLER!!!");
    }
}
