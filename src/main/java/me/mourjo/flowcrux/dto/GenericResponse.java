package me.mourjo.flowcrux.dto;

import lombok.Builder;

@Builder
public record GenericResponse(String key, String value, String message, Long operationId) {

}
