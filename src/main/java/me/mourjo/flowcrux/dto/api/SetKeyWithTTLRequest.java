package me.mourjo.flowcrux.dto.api;

public record SetKeyWithTTLRequest(String key, String value, Integer ttlMillis) {

}
