package de.mtag.cwahlmann.test.taschenrechner.delegate;

import de.mtag.cwahlmann.test.taschenrechner.TaschenrechnerConstants;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProcessEngineExtension.class)
public class TaschenrechnerDiagramTest {

    private ProcessEngine processEngine;

    private static final JavaDelegate NOOP_DELEGATE = execution -> {
    };

    @BeforeEach
    void init() {
        BpmnAwareTests.init(processEngine);
        Mocks.register(TestConstants.LOGGEEINGABENDELEGATE, NOOP_DELEGATE);
        Mocks.register(TestConstants.ERRECHNEERGEBNISDELEGATE, NOOP_DELEGATE);
        Mocks.register(TestConstants.LOGGEERGEBNISDELEGATE, NOOP_DELEGATE);
        Mocks.register(TestConstants.LOGGEDIEANTWORTDELEGATE, NOOP_DELEGATE);
        Mocks.register(TestConstants.ERZEUGEFEHLERNACHRICHTDELEGATE, NOOP_DELEGATE);
    }

    @Test
    @Deployment(resources = TaschenrechnerConstants.BPMN)
    void happyPathTest() {
        var instance = BpmnAwareTests.runtimeService().startProcessInstanceByKey(TaschenrechnerConstants.PROZESS_ID);

        BpmnAwareTests.assertThat(instance).isNotEnded();
        BpmnAwareTests.assertThat(instance).isWaitingAt(TestConstants.ERFASSEEINGABENTASK);

        BpmnAwareTests.complete(BpmnAwareTests.task(TestConstants.ERFASSEEINGABENTASK));

        BpmnAwareTests.assertThat(instance).isWaitingAt(TestConstants.LOGGEEINGABENTASK);
    }

    @Test
    @Deployment(resources = TaschenrechnerConstants.BPMN)
    void fourtytwoPathTest() {
        var instance = BpmnAwareTests.runtimeService().startProcessInstanceByKey(TaschenrechnerConstants.PROZESS_ID);

        Mocks.register(TestConstants.ERRECHNEERGEBNISDELEGATE, (JavaDelegate) execution -> execution.setVariable("z", 42L));

        BpmnAwareTests.assertThat(instance).isNotEnded();
        BpmnAwareTests.assertThat(instance).isWaitingAt(TestConstants.ERFASSEEINGABENTASK);

        BpmnAwareTests.complete(BpmnAwareTests.task(TestConstants.ERFASSEEINGABENTASK));
        BpmnAwareTests.assertThat(instance).isWaitingAt(TestConstants.LOGGEEINGABENTASK);

        BpmnAwareTests.execute(BpmnAwareTests.job(TestConstants.LOGGEEINGABENTASK));
        BpmnAwareTests.assertThat(instance).isWaitingAt(TestConstants.ERRECHNEERGEBNISTASK);

        BpmnAwareTests.execute(BpmnAwareTests.job(TestConstants.ERRECHNEERGEBNISTASK));
        BpmnAwareTests.assertThat(instance).isWaitingAt(TestConstants.LOGGEDIEANTWORTTASK);

        BpmnAwareTests.execute(BpmnAwareTests.job(TestConstants.LOGGEDIEANTWORTTASK));
        BpmnAwareTests.assertThat(instance).isEnded();
    }
}
