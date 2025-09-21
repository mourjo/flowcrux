package me.mourjo.flowcrux.web;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.dto.GenericResponse;
import me.mourjo.flowcrux.dto.KeyValueWithTTLOperation;
import me.mourjo.flowcrux.dto.SetWithTTLRequest;
import me.mourjo.flowcrux.workers.DeleteTranslations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Controller {

    public static final String BPMN_PROCESS = "expiring-keys";
    @Autowired
    DeleteTranslations deleteTranslations;
    @Autowired
    private ZeebeClient zeebeClient;

    @PostMapping("set-key-with-ttl")
    ResponseEntity<GenericResponse> setKeyWithTTL(@RequestBody SetWithTTLRequest request) {
        log.info("Processing request {}", request);
        var ttlMillis = request.ttlMillis();
        var eta = LocalDateTime.now().plus(ttlMillis, ChronoUnit.MILLIS);

        var variables = KeyValueWithTTLOperation.builder()
            .ttl(Duration.ofMillis(request.ttlMillis()))
            .key(request.key())
            .value(request.value())
            .build();

        var event = zeebeClient.newCreateInstanceCommand()
            .bpmnProcessId(BPMN_PROCESS)
            .latestVersion()
            .variables(variables)
            .send()
            .join();

        var id = event.getProcessInstanceKey();
        return ResponseEntity.ok(
            GenericResponse.builder()
                .key(request.key())
                .value(request.value())
                .message("Created key with TTL expiry at %s".formatted(eta))
                .instanceId(id)
                .build()
        );
    }


}
