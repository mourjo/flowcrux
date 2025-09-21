package me.mourjo.flowcrux.web;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.dto.KeyValueWithTTLOperation;
import me.mourjo.flowcrux.dto.api.GenericResponse;
import me.mourjo.flowcrux.dto.api.SetKeyRequest;
import me.mourjo.flowcrux.dto.api.SetKeyWithTTLRequest;
import me.mourjo.flowcrux.services.KeyValueStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class Controller {

    public static final String BPMN_PROCESS = "expiring-keys";
    KeyValueStorageService keyValueStorageService;
    private ZeebeClient zeebeClient;

    @PostMapping("set-key-with-ttl")
    ResponseEntity<GenericResponse> setKeyWithTTL(@RequestBody SetKeyWithTTLRequest request) {
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
                .operationId(id)
                .build()
        );
    }

    @PostMapping("set-key")
    ResponseEntity<GenericResponse> setKey(@RequestBody SetKeyRequest request) {
        var updatedEntity = keyValueStorageService.store(request.key(), request.value());

        return ResponseEntity.ok(
            GenericResponse.builder()
                .key(request.key())
                .value(request.value())
                .message("Created key %s".formatted(request.key()))
                .operationId(updatedEntity.getId())
                .build()
        );
    }

    @GetMapping("{key}")
    ResponseEntity<GenericResponse> getValueForKey(@PathVariable String key) {
        var kv = keyValueStorageService.retrieve(key);
        return ResponseEntity.ok(
            GenericResponse.builder()
                .key(kv.getKey())
                .value(kv.getValue())
                .message("This was created at %s, last updated at %s".formatted(kv.getCreatedAt(), kv.getUpdatedAt()))
                .build()
        );
    }

}
