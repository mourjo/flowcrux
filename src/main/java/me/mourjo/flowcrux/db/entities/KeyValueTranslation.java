package me.mourjo.flowcrux.db.entities;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("kv_store_translations")
@Getter
@Setter
@Builder
public class KeyValueTranslation {

    @Id
    @Column("id")
    private Long id;

    @Column("kv_key")
    private String key;

    @Column("lang")
    private String language;

    @Column("translation")
    private String translation;

    @Column("key_version")
    private Long version;

    @Column("created_at")
    private OffsetDateTime createdAt;

    public KeyValueTranslation(Long id, String key, String language, String translation, Long version, OffsetDateTime createdAt) {
        this.id = id;
        this.key = key;
        this.language = language;
        this.translation = translation;
        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
        if (version == null) {
            version = 1L;
        }
        this.version = version;
        this.createdAt = createdAt;
    }
}
