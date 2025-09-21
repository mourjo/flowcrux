package me.mourjo.flowcrux.services;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.db.KeyValueEntity;
import me.mourjo.flowcrux.db.KeyValueRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KeyValueStorageService {

    private KeyValueRepository repository;

    public KeyValueEntity store(String key, String value) {
        log.info("Storing key `{}` with value `{}`", key, value);
        var existingEntity = repository.findByKey(key);
        KeyValueEntity updatedEntity;

        if (existingEntity != null) {
            existingEntity.setValue(value);
            existingEntity.setUpdatedAt(OffsetDateTime.now());
            updatedEntity = existingEntity;

        } else {
            updatedEntity = KeyValueEntity.builder()
                .key(key)
                .value(value)
                .build();
        }

        return repository.save(updatedEntity);
    }

    public KeyValueEntity retrieve(String key) {
        log.info("Retrieving key `{}`", key);
        return repository.findByKey(key);
    }

    public void delete(String key) {
        log.info("Deleting key `{}`", key);
        repository.deleteByKey(key);
    }

}
