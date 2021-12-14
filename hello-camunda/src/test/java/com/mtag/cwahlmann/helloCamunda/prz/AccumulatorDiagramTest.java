package com.mtag.cwahlmann.helloCamunda.prz;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProcessEngineExtension.class)
public class AccumulatorDiagramTest {

    public static final String ACCUMULATOR_BPMN = "META-INF/processes/accumulator.bpmn";

    public static final String LOGGE_EINGABEN_DELEGATE = "loggeEingabenDelegate";
    public static final String ADDIERE_EINGABEN_DELEGATE = "addiereEingabenDelegate";
    public static final String LOGGE_AUSGABE_DELEGATE = "loggeAusgabeDelegate";

    public static final String LOGGE_EINGABEN_TASK = "LoggeEingabenTask";
    public static final String ADDIERE_EINGABEN_TASK = "AddiereEingabenTask";
    public static final String LOGGE_AUSGABE_TASK = "LoggeAusgabeTask";

    private ProcessEngine processEngine;

    private static final JavaDelegate NOOP_DELEGATE = execution -> {
    };

    @BeforeEach
    public void init() {
        AbstractAssertions.init(processEngine);
        Mocks.register(LOGGE_EINGABEN_DELEGATE, NOOP_DELEGATE);
        Mocks.register(ADDIERE_EINGABEN_DELEGATE, NOOP_DELEGATE);
        Mocks.register(LOGGE_AUSGABE_DELEGATE, NOOP_DELEGATE);
    }

    @Test
    @Deployment(resources = ACCUMULATOR_BPMN)
    void testGood() {
        ProcessInstance instance = runtimeService().startProcessInstanceByKey(AccumulatorProcessConstants.PROCESS_ID);

        assertThat(instance).isNotEnded();
        execute(job(LOGGE_EINGABEN_TASK));

        assertThat(instance).isWaitingAt(ADDIERE_EINGABEN_TASK);
        execute(job(ADDIERE_EINGABEN_TASK));

        assertThat(instance).isWaitingAt(LOGGE_AUSGABE_TASK);
        execute(job(LOGGE_AUSGABE_TASK));

        assertThat(instance).isEnded();
    }
}
