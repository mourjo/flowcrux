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

    public void createHistory(String key, String value, long keyVersion) {
        log.info("Creating history for key `{}` and value `{}`", key, value);
        repository.save(
            KeyValueHistoryEntity.builder()
                .key(key)
                .value(value)
                .version(keyVersion)
                .build()
        );
    }

    public void deleteHistory(String key, long version) {
        boolean concurrentUpdate = getHistory(key).stream().anyMatch(history -> history.getVersion() > version);
        if (!concurrentUpdate) {
            repository.deleteHistoricalValues(key, version);
            log.info("Deleted history of key `{}` version `{}`", key, version);
        } else {
            log.warn("Version mismatch: Not deleing key `{}` version `{}`", key, version);
        }
    }

    public List<KeyValueHistoryEntity> searchHistory(String text) {
        return repository.findByKeyOrValueContaining(text, text);
    }
}
