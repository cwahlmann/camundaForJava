package com.mtag.cwahlmann.helloCamunda.prz.calculator2.delegate;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.extension.mockito.delegate.DelegateExecutionFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BerechneErgebnis2DelegateTest {

    static Stream<Arguments> provideTestdataGood() {
        return Stream.of(
                Arguments.of(1, 2, "PLUS", 3),
                Arguments.of(1, 2, "MINUS", -1),
                Arguments.of(2, 3, "MULTIPLY", 6),
                Arguments.of(10, 3, "DIVIDE", 3),
                Arguments.of(4, 0, "SQUARE", 16),
                Arguments.of(4, 0, "SQUARE_ROOT", 2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestdataGood")
    void testGood(long x, long y, String operation, long z) throws Exception {
        var execution = DelegateExecutionFake.of()
                .withVariable("x", Long.valueOf(x))
                .withVariable("y", Long.valueOf(y))
                .withVariable("operation", operation);
        new BerechneErgebnisDelegate2().execute(execution);
        Assertions.assertTrue(execution.hasVariable("z"));
        Assertions.assertEquals(Long.valueOf(z), execution.getVariable("z"));
    }

    static Stream<Arguments> provideTestdataBad() {
        return Stream.of(
                Arguments.of(1, 0, "DIVIDE", "1"),
                Arguments.of(-1, 0, "SQUARE_ROOT", "2"),
                Arguments.of(0, 0, "IRGENDWAS", "99")
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestdataBad")
    void testBpmnError(long x, long y, String operation, String errorcode) throws Exception {
        var execution = DelegateExecutionFake.of()
                .withVariable("x", x)
                .withVariable("y", y)
                .withVariable("operation", operation);
        try {
            new BerechneErgebnisDelegate2().execute(execution);
            Assertions.fail("Expected errorcode " + errorcode);
        } catch (BpmnError e) {
            Assertions.assertEquals(errorcode, e.getErrorCode());
        }
    }
}
