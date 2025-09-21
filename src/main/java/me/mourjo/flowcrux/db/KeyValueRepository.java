package me.mourjo.flowcrux.db;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyValueRepository extends ListCrudRepository<KeyValueEntity, Long> {

    @Query("""
        SELECT * FROM kv_store WHERE kv_key = :key;
        """)
    KeyValueEntity findByKey(@Param("key") String key);

}
