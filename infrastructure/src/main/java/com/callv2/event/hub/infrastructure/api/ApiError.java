package com.callv2.event.hub.infrastructure.api;

import java.util.List;

import com.callv2.event.hub.domain.exception.DomainException;

public record ApiError(String message, List<String> errors) {

    public static ApiError with(final String message) {
        return new ApiError(message, List.of());
    }

    public static ApiError from(final DomainException ex) {
        return new ApiError(ex.getMessage(), ex.getErrors().stream().map(DomainException.Error::message).toList());
    }

}
