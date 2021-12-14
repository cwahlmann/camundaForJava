package com.mtag.cwahlmann.helloCamunda.prz.delegate;

import org.camunda.bpm.extension.mockito.delegate.DelegateExecutionFake;
import org.junit.jupiter.api.Test;

public class HelloDelegateTest {
    @Test
    void HelloDelegateTest() {
        new AddiereEingabenDelegate().execute(DelegateExecutionFake.of());
    }
}
