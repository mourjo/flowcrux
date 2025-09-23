package me.mourjo.flowcrux.services;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.db.entities.KeyValue;
import me.mourjo.flowcrux.db.repositories.KeyValueRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KeyValueStorageService {

    private KeyValueRepository repository;

    public KeyValue store(String key, String value) {
        log.info("Storing key `{}` with value `{}`", key, value);
        var existingEntity = repository.findByKey(key);
        KeyValue updatedEntity;

        if (existingEntity != null) {
            existingEntity.setValue(value);
            existingEntity.setUpdatedAt(OffsetDateTime.now());
            updatedEntity = existingEntity;
            updatedEntity.setVersion(existingEntity.getVersion() + 1);

        } else {
            updatedEntity = KeyValue.builder()
                .key(key)
                .value(value)
                .build();
        }

        return repository.save(updatedEntity);
    }

    public KeyValue retrieve(String key) {
        log.info("Retrieving key `{}`", key);
        return repository.findByKey(key);
    }

    public void delete(String key, long version) {
        var existingEntity = retrieve(key);
        if (existingEntity != null && existingEntity.getVersion() == version) {
            log.info("Deleting key `{}` version {}", key, version);
            repository.deleteByKey(key, version);
        } else {
            log.warn("Version mismatch: Not deleing key `{}` version `{}`", key, version);
        }
    }

}
