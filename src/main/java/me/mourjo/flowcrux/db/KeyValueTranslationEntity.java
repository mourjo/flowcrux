package me.mourjo.flowcrux.db;

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
public class KeyValueTranslationEntity {

    @Id
    @Column("id")
    private Long id;
    @Column("kv_key")
    private String key;
    @Column("lang")
    private String language;
    @Column("translation")
    private String translation;
    @Column("created_at")
    private OffsetDateTime createdAt;

    public KeyValueTranslationEntity(Long id, String key, String language, String translation, OffsetDateTime createdAt) {
        this.id = id;
        this.key = key;
        this.language = language;
        this.translation = translation;
        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
        this.createdAt = createdAt;
    }
}
