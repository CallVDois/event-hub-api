package com.callv2.eventhub.domain.event;

import com.callv2.eventhub.domain.ValueObject;
import com.callv2.eventhub.domain.validation.ValidationError;
import com.callv2.eventhub.domain.validation.ValidationHandler;

public record Key(String domain, String entity, String action) implements ValueObject {

    public Key {
        domain = domain.trim().toLowerCase();
        entity = entity.trim().toLowerCase();
        action = action.trim().toLowerCase();
    }

    public static Key from(final String domain, final String entity, final String action) {
        return new Key(domain, entity, action);
    }

    @Override
    public void validate(final ValidationHandler aHandler) {

        if (domain == null || domain.isBlank())
            aHandler.append(ValidationError.with("domain must not be null or empty"));

        if (entity == null || entity.isBlank())
            aHandler.append(ValidationError.with("entity must not be null or empty"));

        if (action == null || action.isBlank())
            aHandler.append(ValidationError.with("action must not be null or empty"));

    }

    public String qualifiedName() {
        return domain() + "." + entity() + "." + action();
    }

}
