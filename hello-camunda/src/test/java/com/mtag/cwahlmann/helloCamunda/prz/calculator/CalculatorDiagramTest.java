package com.mtag.cwahlmann.helloCamunda.prz.calculator;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.mtag.cwahlmann.helloCamunda.prz.calculator.CalculatorTestConstants.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@ExtendWith(ProcessEngineExtension.class)
public class CalculatorDiagramTest {

    private ProcessEngine processEngine;

    private static final JavaDelegate NOOP_DELEGATE = execution -> {
    };

    @BeforeEach
    public void init() {
        AbstractAssertions.init(processEngine);
        Mocks.register(LOGGE_EINGABEN_DELEGATE, NOOP_DELEGATE);
        Mocks.register(BERECHNE_ERGEBNIS_DELEGATE, NOOP_DELEGATE);
        Mocks.register(LOGGE_ERGEBNIS_DELEGATE, NOOP_DELEGATE);
    }

    @Test
    @Deployment(resources = ACCUMULATOR_BPMN)
    void testGood() {
        ProcessInstance instance = runtimeService().startProcessInstanceByKey(CalculatorProcessConstants.PROCESS_ID);

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
}
