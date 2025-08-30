package com.callv2.event.hub.infrastructure.api;

import java.util.List;

public record ApiError(String message, List<String> errors) {

    static ApiError with(final String message) {
        return new ApiError(message, List.of());
    }

}
