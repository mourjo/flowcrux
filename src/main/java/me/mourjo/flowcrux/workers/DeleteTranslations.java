package me.mourjo.flowcrux.workers;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.dto.KeyValueWithTTLOperation;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteTranslations {

    @SneakyThrows
    @JobWorker(type = "delete-translations")
    public KeyValueWithTTLOperation deleteTranslations(@VariablesAsType KeyValueWithTTLOperation operation) {
        log.info("Starting {}", operation);

        return operation;
    }
}

