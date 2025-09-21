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
    private Long id;

    @Column("kv_key")
    private String key;

    @Column("kv_value")
    private String value;

    @Column("created_at")
    private OffsetDateTime createdAt;

    @Column("updated_at")
    private OffsetDateTime updatedAt;

    @Column("deleted_at")
    private OffsetDateTime deletedAt;

    public KeyValueEntity(Long id, String key, String value, OffsetDateTime createdAt, OffsetDateTime updatedAt, OffsetDateTime deletedAt) {
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

        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
}