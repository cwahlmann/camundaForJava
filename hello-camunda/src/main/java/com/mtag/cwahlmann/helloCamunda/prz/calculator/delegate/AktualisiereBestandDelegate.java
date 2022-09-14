package com.mtag.cwahlmann.helloCamunda.prz.calculator.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class AktualisiereBestandDelegate implements JavaDelegate {
    private final static Logger log = getLogger(AktualisiereBestandDelegate.class);

    private final BestandService bestandService;
    private final RechnungService rechnungService;

    @Autowired
    public AktualisiereBestandDelegate(BestandService bestandService,
                                       RechnungService rechnungService) {
        this.bestandService = bestandService;
        this.rechnungService = rechnungService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        var auftrag = (Auftrag) delegateExecution.getVariable("VAR_AUFTRAG");
        if (!auftrag.isFinished()) {
            log.warn("Auftrag {} ist nicht abgeschlossen. Beende.", auftrag.getId());
            return;
        }
        var rechnung = rechnungService.erstelle(auftrag);
        log.info("Rechnung zu Auftrag {} erstellt: {} - {}", auftrag.getId(),
                rechnung.getPositionen(), rechnung.getBetrag());
        bestandService.aktualisiere(auftrag);
        log.warn("Auftrag {}: Bestand wurde aktualisiert.", auftrag.getId());
    }
}
