package com.mtag.cwahlmann.helloCamunda.prz.calculator;

public class CalculatorTestConstants {
    private CalculatorTestConstants() {
    }

    public static final String ACCUMULATOR_BPMN = "META-INF/processes/calculator.bpmn";

    public static final String LOGGE_EINGABEN_DELEGATE = "loggeEingabenDelegate";
    public static final String BERECHNE_ERGEBNIS_DELEGATE = "berechneErgebnisDelegate";
    public static final String LOGGE_ERGEBNIS_DELEGATE = "loggeErgebnisDelegate";

    public static final String ERFASSE_WERTE_TASK = "ErfasseWerteTask";
    public static final String LOGGE_EINGABEN_TASK = "LoggeEingabenTask";
    public static final String BERECHNE_ERGEBNIS_TASK = "BerechneErgebnisTask";
    public static final String LOGGE_ERGEBNIS_TASK = "LoggeErgebnisTask";

}
