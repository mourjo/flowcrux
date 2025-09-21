package me.mourjo.flowcrux.workers;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.dto.KeyValueWithTTLOperation;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeleteKey {

    @SneakyThrows
    @JobWorker(type = "delete-key")
    public KeyValueWithTTLOperation deleteKey(@VariablesAsType KeyValueWithTTLOperation operation) {
        log.info("Starting {}", operation);

        return operation;
    }
}
