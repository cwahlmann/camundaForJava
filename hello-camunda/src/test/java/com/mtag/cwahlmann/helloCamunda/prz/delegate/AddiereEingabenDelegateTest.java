package com.mtag.cwahlmann.helloCamunda.prz.delegate;

import org.camunda.bpm.extension.mockito.delegate.DelegateExecutionFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddiereEingabenDelegateTest {
    @Test
    void HelloDelegateTest() {
        var execution = DelegateExecutionFake.of().withVariable("x",2).withVariable("y", 1);
        new AddiereEingabenDelegate().execute(execution);
        Assertions.assertTrue(execution.hasVariable("z"));
        Assertions.assertEquals(3, execution.getVariable("z"));
    }
}
