package me.mourjo.flowcrux.db;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("kv_store")
@Getter
@Setter
@Builder
public class KeyValueEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("kv_key")
    private String key;

    @Column("kv_value")
    private String value;

    @Column("version")
    private Long version;

    @Column("created_at")
    private OffsetDateTime createdAt;

    @Column("updated_at")
    private OffsetDateTime updatedAt;

    public KeyValueEntity(Long id, String key, String value, Long version, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.key = key;
        this.value = value;

        var now = OffsetDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }

        if (updatedAt == null) {
            updatedAt = now;
        }

        if (version == null) {
            version = 1L;
        }

        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.version = version;
    }
}