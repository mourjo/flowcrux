package me.mourjo.flowcrux.db.entities;

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
public class KeyValueHistory {

    @Id
    @Column("id")
    private Long id;

    @Column("kv_key")
    private String key;

    @Column("kv_value")
    private String value;

    @Column("key_version")
    private Long version;

    @Column("created_at")
    private OffsetDateTime createdAt;

    public KeyValueHistory(Long id, String key, String value, Long version, OffsetDateTime createdAt) {
        this.id = id;
        this.key = key;
        this.value = value;
        if (version == null) {
            version = 1L;
        }

        this.version = version;

        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
        this.createdAt = createdAt;
    }
}
