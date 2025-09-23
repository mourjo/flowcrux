package me.mourjo.flowcrux.db.repositories;

import me.mourjo.flowcrux.db.entities.KeyValueTranslation;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyValueTranslationsRepository extends ListCrudRepository<KeyValueTranslation, Long> {

}


