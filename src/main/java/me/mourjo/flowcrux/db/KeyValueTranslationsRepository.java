package me.mourjo.flowcrux.db;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyValueTranslationsRepository extends ListCrudRepository<KeyValueTranslationEntity, Long> {

}


