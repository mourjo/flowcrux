package me.mourjo.flowcrux.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.db.KeyValueTranslationsRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KeyValueTranslationsService {

    private KeyValueTranslationsRepository repository;

    public void addTranslations(String key, String value) {
        log.info("Adding translations for key `{}`, value `{}`", key, value);
    }

    public void deleteTranslations(String key) {
        log.info("Deleting translations for key `{}`", key);
    }
}
