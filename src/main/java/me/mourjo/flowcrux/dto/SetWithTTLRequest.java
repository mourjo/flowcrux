package me.mourjo.flowcrux.dto;

public record SetWithTTLRequest(String key, String value, Integer ttlMillis) {

}
