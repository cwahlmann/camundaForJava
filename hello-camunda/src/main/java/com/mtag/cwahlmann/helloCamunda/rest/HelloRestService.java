package com.mtag.cwahlmann.helloCamunda.rest;

import com.mtag.cwahlmann.helloCamunda.prz.hello.HelloProcessConstants;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;

@RestController
public class HelloRestService {
    public static final Logger log = LoggerFactory.getLogger(HelloRestService.class);

    @Autowired
    private RuntimeService runtimeService;

    @GetMapping("/hello/start")
    public String greeting() {
        try {
            ProcessInstance instance = runtimeService.startProcessInstanceByKey(HelloProcessConstants.PROCESS_ID);
            return "{\"pid\":\""+ HelloProcessConstants.PROCESS_ID+"\", \"InstanceId\":\"" + instance.getProcessInstanceId() + "\"}";
        } catch (Exception e) {
            log.error("Fehler beim Starten des Prozesses '" + HelloProcessConstants.PROCESS_ID + "'", e);
            throw (e);
        }
    }
}
