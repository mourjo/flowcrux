package me.mourjo.flowcrux.db;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("kv_store_history")
@Getter
@Setter
@Builder
public class KeyValueHistoryEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("kv_key")
    private String key;

    @Column("kv_value")
    private String value;

    @Column("created_at")
    private OffsetDateTime createdAt;

    public KeyValueHistoryEntity(Long id, String key, String value, OffsetDateTime createdAt) {
        this.id = id;
        this.key = key;
        this.value = value;
        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
        this.createdAt = createdAt;
    }
}
