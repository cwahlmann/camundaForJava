package com.mtag.cwahlmann.helloCamunda.prz.calculator.delegate;

import org.springframework.stereotype.Component;

@Component
public class RechnungServiceImpl implements RechnungService {
    @Override
    public Rechnung erstelle (Auftrag auftrag) {
        return new Rechnung();
    }
}
