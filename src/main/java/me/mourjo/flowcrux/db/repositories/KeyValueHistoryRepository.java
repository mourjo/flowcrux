package me.mourjo.flowcrux.db.repositories;

import java.util.List;

import me.mourjo.flowcrux.db.entities.KeyValueHistory;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyValueHistoryRepository extends ListCrudRepository<KeyValueHistory, Long> {

    @Query("""
        SELECT * FROM kv_store_history WHERE kv_key=:key
        """)
    List<KeyValueHistory> getHistoricalValues(@Param("key") String key);


    List<KeyValueHistory> findByKeyOrValueContaining(String keyPhrase, String valuePhrase);


    @Modifying
    @Query("""
        DELETE FROM kv_store_history WHERE kv_key=:key AND key_version <= :version
        """)
    int deleteHistoricalValues(@Param("key") String key, @Param("version") long version);
}
