package me.mourjo.flowcrux.services;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.db.entities.KeyValueTranslation;
import me.mourjo.flowcrux.db.repositories.KeyValueTranslationsRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KeyValueTranslationsService {

    private KeyValueTranslationsRepository repository;

    @SneakyThrows
    public void addTranslations(String key, String value) {
        log.info("Adding translations for key `{}`, value `{}`", key, value);
        Thread.sleep(60000);
    }

    @SneakyThrows
    public void deleteTranslations(String key) {
        log.info("Deleting translations for key `{}`", key);
        Thread.sleep(60000);
    }

    @SneakyThrows
    public List<KeyValueTranslation> searchTranslations(String key) {
        return List.of();
    }
}
