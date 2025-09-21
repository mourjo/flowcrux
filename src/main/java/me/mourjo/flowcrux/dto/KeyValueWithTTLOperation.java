package me.mourjo.flowcrux.dto;

import java.time.Duration;

import lombok.Builder;

@Builder
public record KeyValueWithTTLOperation(String key, String value, Duration ttl, Long version) {

    public static KeyValueWithTTLOperationBuilder buildFrom(KeyValueWithTTLOperation op) {
        return KeyValueWithTTLOperation.builder()
            .key(op.key)
            .value(op.value)
            .ttl(op.ttl)
            .version(op.version());
    }
}
