package me.mourjo.flowcrux.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.db.KeyValueEntity;
import me.mourjo.flowcrux.db.KeyValueHistoryEntity;
import me.mourjo.flowcrux.db.KeyValueTranslationEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KeyValueSearchService {

    KeyValueTranslationsService keyValueTranslationsService;
    KeyValueHistoryService keyValueHistoryService;
    KeyValueStorageService keyValueStorageService;

    public List<KeyValueEntity> search(String text) {
        Set<String> matchingKeys = new HashSet<>();
        matchingKeys.addAll(keyValueTranslationsService.searchTranslations(text).stream().map(KeyValueTranslationEntity::getKey).collect(Collectors.toSet()));
        matchingKeys.addAll(keyValueHistoryService.searchHistory(text).stream().map(KeyValueHistoryEntity::getKey).collect(Collectors.toSet()));

        return matchingKeys.stream()
            .map(keyValueStorageService::retrieve)
            .toList();
    }
}
