package de.mtag.cwahlmann.test.taschenrechner.delegate;

import de.mtag.cwahlmann.test.taschenrechner.TaschenrechnerConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ErzeugeFehlernachrichtDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var errorcode = execution.getVariable(TaschenrechnerConstants.VAR_ERRORCODE);
        var errormsg = execution.getVariable(TaschenrechnerConstants.VAR_ERRORMSG);

        var nachricht = "Die Eingabe ist nicht korrekt. Bitte wiederholen! (" + errorcode + ": " + errormsg + ")";

        execution.setVariable(TaschenrechnerConstants.VAR_NACHRICHT, nachricht);
    }
}
