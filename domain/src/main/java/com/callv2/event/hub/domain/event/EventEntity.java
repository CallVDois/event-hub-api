package com.callv2.event.hub.domain.event;

import com.callv2.event.hub.domain.ValueObject;
import com.callv2.event.hub.domain.validation.ValidationHandler;

public record EventEntity(String domain, String type, String id) implements ValueObject {

    public EventEntity {
        domain = domain.trim().toLowerCase();
    }

    public static EventEntity from(final String domain, final String type, final String id) {
        return new EventEntity(domain, type, id);
    }

    @Override
    public void validate(final ValidationHandler aHandler) {
        // TODO implements validate
    }

}
