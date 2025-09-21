package me.mourjo.flowcrux.services;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.db.KeyValueHistoryEntity;
import me.mourjo.flowcrux.db.KeyValueHistoryRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KeyValueHistoryService {

    private KeyValueHistoryRepository repository;

    public List<KeyValueHistoryEntity> getHistory(String key) {
        log.info("Retrieving history of key `{}`", key);
        return repository.getHistoricalValues(key);
    }

    public void createHistory(String key, String value) {
        log.info("Creating history for key `{}` and value `{}`", key, value);
        repository.save(
            KeyValueHistoryEntity.builder()
                .key(key)
                .value(value)
                .build()
        );
    }

    public void deleteHistory(String key) {
        log.info("Deleting history of key `{}`", key);
        int rows = repository.deleteHistoricalValues(key);
        log.info("Deleted {} rows", rows);
    }
}
