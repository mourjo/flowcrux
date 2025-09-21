package me.mourjo.flowcrux.dto.api;

import lombok.Builder;

@Builder
public record GenericResponse(String key, String value, String message, Long operationId) {

}
