package com.mtag.cwahlmann.helloCamunda.prz.lookup;

import jdk.dynalink.linker.support.Lookup;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LookupService {
    private static final Map<String, String> ADDRESSES_BY_NAME = Map.of(
            "Peter Hase", "Sandkuhle 3, 1357 Tupfingen",
            "Lisa Lustig", "En de Retematäng, 40213 Düsseldorf"
    );

    private final RuntimeService runtimeService;

    public LookupService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void findByName(String businessKey, String name) {
        var result = ADDRESSES_BY_NAME.entrySet().stream()
                .filter(e -> "*".equals(name) || e.getKey().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)))
                .map(e -> e.getKey() + ", " + e.getValue()).collect(Collectors.toList());
        runtimeService.createMessageCorrelation(LookupConstants.MESSAGE_NAME)
                .processInstanceBusinessKey(businessKey)
                .setVariable(LookupConstants.VAR_SUCHERGEBNIS, result)
                .correlate();
    }
}
