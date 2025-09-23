package me.mourjo.flowcrux.db.repositories;

import me.mourjo.flowcrux.db.entities.KeyValue;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyValueRepository extends ListCrudRepository<KeyValue, Long> {

    @Query("""
        SELECT * FROM kv_store WHERE kv_key = :key;
        """)
    KeyValue findByKey(@Param("key") String key);

    @Modifying
    @Query("""
        DELETE FROM kv_store WHERE kv_key = :key AND version <= :version;
        """)
    int deleteByKey(@Param("key") String key, @Param("version") long version);

}
