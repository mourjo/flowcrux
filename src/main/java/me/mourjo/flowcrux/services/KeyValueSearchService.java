package me.mourjo.flowcrux.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mourjo.flowcrux.db.entities.KeyValue;
import me.mourjo.flowcrux.db.entities.KeyValueHistory;
import me.mourjo.flowcrux.db.entities.KeyValueTranslation;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KeyValueSearchService {

    KeyValueTranslationsService keyValueTranslationsService;
    KeyValueHistoryService keyValueHistoryService;
    KeyValueStorageService keyValueStorageService;

    public List<KeyValue> search(String text) {
        Set<String> matchingKeys = new HashSet<>();
        matchingKeys.addAll(keyValueTranslationsService.searchTranslations(text).stream().map(KeyValueTranslation::getKey).collect(Collectors.toSet()));
        matchingKeys.addAll(keyValueHistoryService.searchHistory(text).stream().map(KeyValueHistory::getKey).collect(Collectors.toSet()));

        return matchingKeys.stream()
            .map(keyValueStorageService::retrieve)
            .toList();
    }
}
