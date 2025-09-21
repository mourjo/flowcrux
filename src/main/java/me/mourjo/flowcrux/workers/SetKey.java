package me.mourjo.flowcrux.workers;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.dto.KeyValueWithTTLOperation;
import me.mourjo.flowcrux.services.KeyValueStorageService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class SetKey {

    KeyValueStorageService keyValueStorageService;

    @SneakyThrows
    @JobWorker(type = "set-key")
    public KeyValueWithTTLOperation setKey(@VariablesAsType KeyValueWithTTLOperation operation) {
        log.info("Starting {}", operation);
        keyValueStorageService.store(operation.key(), operation.value());
        return operation;
    }
}
