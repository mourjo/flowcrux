package me.mourjo.flowcrux.workers;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.dto.KeyValueWithTTLOperation;
import me.mourjo.flowcrux.services.KeyValueHistoryService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class UpdateHistory {

    KeyValueHistoryService keyValueHistoryService;

    @SneakyThrows
    @JobWorker(type = "update-history")
    public KeyValueWithTTLOperation updateHistory(@VariablesAsType KeyValueWithTTLOperation operation) {
        log.info("Starting {}", operation);
        keyValueHistoryService.createHistory(operation.key(), operation.value(), operation.version());
        return operation;
    }

}
