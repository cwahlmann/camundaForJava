package de.mtag.cwahlmann.test.taschenrechner.delegate;

import de.mtag.cwahlmann.test.taschenrechner.TaschenrechnerConstants;
import org.camunda.bpm.extension.mockito.delegate.DelegateExecutionFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ErrechneErgebnisDelegateTest {
    @Test
    void testGood() throws Exception {
        var execution = DelegateExecutionFake.of()
                .withVariable(TaschenrechnerConstants.VAR_X, 1L)
                .withVariable(TaschenrechnerConstants.VAR_Y, 2L)
                .withVariable(TaschenrechnerConstants.VAR_O, "PLUS");

        new ErrechneErgebnisDelegate(List.of("A", "B")).execute(execution);

        var z = (Long) execution.getVariable(TaschenrechnerConstants.VAR_Z);
        Assertions.assertEquals(3L, z);
    }
}
