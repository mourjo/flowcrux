package me.mourjo.flowcrux.workers;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.SneakyThrows;
import me.mourjo.flowcrux.dto.KeyValueWithTTLOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeleteHistory {

    private final static Logger log = LoggerFactory.getLogger(DeleteHistory.class);

    @SneakyThrows
    @JobWorker(type = "delete-history")
    public KeyValueWithTTLOperation deleteHistory(@VariablesAsType KeyValueWithTTLOperation operation) {
        log.info("Starting {}", operation);

        return operation;
    }
}
