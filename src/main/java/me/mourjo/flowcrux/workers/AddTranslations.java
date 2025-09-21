package me.mourjo.flowcrux.workers;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.dto.KeyValueWithTTLOperation;
import me.mourjo.flowcrux.services.KeyValueTranslationsService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class AddTranslations {

    KeyValueTranslationsService keyValueTranslationsService;

    @SneakyThrows
    @JobWorker(type = "add-translations")
    public KeyValueWithTTLOperation addTranslations(@VariablesAsType KeyValueWithTTLOperation operation) {
        log.info("Starting {}", operation);
        keyValueTranslationsService.addTranslations(operation.key(), operation.value());
        return operation;
    }
}
