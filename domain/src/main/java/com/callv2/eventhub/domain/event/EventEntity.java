package com.callv2.eventhub.domain.event;

import com.callv2.eventhub.domain.ValueObject;
import com.callv2.eventhub.domain.validation.ValidationError;
import com.callv2.eventhub.domain.validation.ValidationHandler;

public record EventEntity(String domain, String type, String id) implements ValueObject {

    public EventEntity {
        domain = domain.trim().toLowerCase();
    }

    public static EventEntity from(final String domain, final String type, final String id) {
        return new EventEntity(domain, type, id);
    }

    @Override
    public void validate(final ValidationHandler aHandler) {

        if (domain == null || domain.isBlank())
            aHandler.append(ValidationError.with("domain must not be null or empty"));

        if (type == null || type.isBlank())
            aHandler.append(ValidationError.with("type must not be null or empty"));

        if (id == null || id.isBlank())
            aHandler.append(ValidationError.with("id must not be null or empty"));

    }

}
