package com.mtag.cwahlmann.helloCamunda.prz.calculator.delegate;

import org.camunda.bpm.extension.mockito.delegate.DelegateExecutionFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BerechneErgebnisDelegateTest {
    @Test
    void testGood() throws Exception {
        var execution = DelegateExecutionFake.of()
                .withVariable("x", 2L)
                .withVariable("y", 1L)
                .withVariable("operation", "PLUS");
        new BerechneErgebnisDelegate().execute(execution);
        Assertions.assertTrue(execution.hasVariable("z"));
        Assertions.assertEquals(3L, execution.getVariable("z"));
    }
}
