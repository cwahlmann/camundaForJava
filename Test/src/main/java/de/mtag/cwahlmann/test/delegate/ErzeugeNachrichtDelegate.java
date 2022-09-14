package de.mtag.cwahlmann.test.delegate;

import de.mtag.cwahlmann.test.HalloProzessContants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ErzeugeNachrichtDelegate  implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.setVariable(HalloProzessContants.VAR_NACHRICHT, "Hallo");
    }
}
