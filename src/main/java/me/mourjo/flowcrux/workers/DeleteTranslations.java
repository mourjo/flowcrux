package me.mourjo.flowcrux.workers;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.dto.KeyValueWithTTLOperation;
import me.mourjo.flowcrux.services.KeyValueTranslationsService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DeleteTranslations {

    KeyValueTranslationsService keyValueTranslationsService;

    @SneakyThrows
    @JobWorker(type = "delete-translations")
    public KeyValueWithTTLOperation deleteTranslations(@VariablesAsType KeyValueWithTTLOperation operation) {
        log.info("Starting {}", operation);
        keyValueTranslationsService.deleteTranslations(operation.key());
        return operation;
    }
}

