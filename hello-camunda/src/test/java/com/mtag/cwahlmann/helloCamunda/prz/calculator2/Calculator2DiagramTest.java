package com.mtag.cwahlmann.helloCamunda.prz.calculator2;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;
import java.util.function.Function;

import static com.mtag.cwahlmann.helloCamunda.prz.calculator2.Calculator2TestConstants.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@ExtendWith(ProcessEngineExtension.class)
public class Calculator2DiagramTest {

    private ProcessEngine processEngine;

    private static final JavaDelegate NOOP_DELEGATE = execution -> {
    };

    private static final Function<Map<String, Object>, JavaDelegate> VARIABLES_DELEGATE = vars -> execution -> vars.forEach(execution::setVariable);

    @BeforeEach
    public void init() {
        BpmnAwareTests.init(processEngine);
        Mocks.register(LOGGE_EINGABEN_DELEGATE2, NOOP_DELEGATE);
        Mocks.register(BERECHNE_ERGEBNIS_DELEGATE2, NOOP_DELEGATE);
        Mocks.register(LOGGE_ERGEBNIS_DELEGATE2, NOOP_DELEGATE);
        Mocks.register(LOGGE_DIE_ANTWORT_DELEGATE, NOOP_DELEGATE);
    }

    @Test
    @Deployment(resources = ACCUMULATOR2_BPMN)
    void testGood() {
        ProcessInstance instance = runtimeService().startProcessInstanceByKey(Calculator2ProcessConstants.PROCESS_ID);

        assertThat(instance).isNotEnded(); // BpmnAwareTests merkt sich statisch die ProcessInstance
        complete(task(ERFASSE_WERTE_TASK)); // und greift hier darauf zurück

        assertThat(instance).isWaitingAt(LOGGE_EINGABEN_TASK);
        execute(job(LOGGE_EINGABEN_TASK));

        assertThat(instance).isWaitingAt(BERECHNE_ERGEBNIS_TASK);
        execute(job(BERECHNE_ERGEBNIS_TASK));

        assertThat(instance).isWaitingAt(LOGGE_ERGEBNIS_TASK);
        execute(job(LOGGE_ERGEBNIS_TASK));

        assertThat(instance).isEnded();
    }

    @Test
    @Deployment(resources = ACCUMULATOR2_BPMN)
    void testEingabefehler() {
        Mocks.register(BERECHNE_ERGEBNIS_DELEGATE2, (JavaDelegate) execution -> {
            throw new BpmnError("test", "test");
        });

        ProcessInstance instance = runtimeService().startProcessInstanceByKey(Calculator2ProcessConstants.PROCESS_ID);

        assertThat(instance).isNotEnded();
        complete(task(ERFASSE_WERTE_TASK));

        assertThat(instance).isWaitingAt(LOGGE_EINGABEN_TASK);
        execute(job(LOGGE_EINGABEN_TASK));

        assertThat(instance).isWaitingAt(BERECHNE_ERGEBNIS_TASK);
        execute(job(BERECHNE_ERGEBNIS_TASK));

        assertThat(instance).isWaitingAt(ERSTELLE_FEHLERMELDUNG_TASK);
        execute(job(ERSTELLE_FEHLERMELDUNG_TASK));

        assertThat(instance).isWaitingAt(ERFASSE_WERTE_TASK);
    }

    @Test
    @Deployment(resources = ACCUMULATOR2_BPMN)
    void testDieAntwort() {
        Mocks.register(BERECHNE_ERGEBNIS_DELEGATE2, VARIABLES_DELEGATE.apply(withVariables("z", 42L)));

        ProcessInstance instance = runtimeService().startProcessInstanceByKey(Calculator2ProcessConstants.PROCESS_ID);

        assertThat(instance).isNotEnded();
        complete(task(ERFASSE_WERTE_TASK));

        assertThat(instance).isWaitingAt(LOGGE_EINGABEN_TASK);
        execute(job(LOGGE_EINGABEN_TASK));

        assertThat(instance).isWaitingAt(BERECHNE_ERGEBNIS_TASK);
        execute(job(BERECHNE_ERGEBNIS_TASK));

        assertThat(instance).isWaitingAt(LOGGE_DIE_ANTWORT_TASK);
        execute(job(LOGGE_DIE_ANTWORT_TASK));

        assertThat(instance).isEnded();
    }
}
