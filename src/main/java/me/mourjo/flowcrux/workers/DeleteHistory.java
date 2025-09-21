package me.mourjo.flowcrux.workers;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.dto.KeyValueWithTTLOperation;
import me.mourjo.flowcrux.services.KeyValueHistoryService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DeleteHistory {


    KeyValueHistoryService keyValueHistoryService;

    @SneakyThrows
    @JobWorker(type = "delete-history")
    public KeyValueWithTTLOperation deleteHistory(@VariablesAsType KeyValueWithTTLOperation operation) {
        log.info("Starting {}", operation);
        keyValueHistoryService.deleteHistory(operation.key());
        return operation;
    }
}
