package me.mourjo.flowcrux.dto;

import java.time.Duration;

import lombok.Builder;

@Builder
public record KeyValueWithTTLOperation(String key, String value, Duration ttl) {

}
