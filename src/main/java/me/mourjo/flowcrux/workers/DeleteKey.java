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
public class DeleteKey {

    KeyValueStorageService keyValueStorageService;

    @SneakyThrows
    @JobWorker(type = "delete-key")
    public KeyValueWithTTLOperation deleteKey(@VariablesAsType KeyValueWithTTLOperation operation) {
        log.info("Starting {}", operation);
        keyValueStorageService.delete(operation.key(), operation.version());
        return operation;
    }
}
