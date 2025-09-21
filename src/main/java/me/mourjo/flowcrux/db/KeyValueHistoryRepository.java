package me.mourjo.flowcrux.db;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyValueHistoryRepository extends ListCrudRepository<KeyValueHistoryEntity, Long> {

    @Query("""
        SELECT * FROM kv_store_history WHERE kv_key=:key
        """)
    List<KeyValueHistoryEntity> getHistoricalValues(@Param("key") String key);


    List<KeyValueHistoryEntity> findByKeyOrValueContaining(String keyPhrase, String valuePhrase);


    @Modifying
    @Query("""
        DELETE FROM kv_store_history WHERE kv_key=:key
        """)
    int deleteHistoricalValues(@Param("key") String key);
}
