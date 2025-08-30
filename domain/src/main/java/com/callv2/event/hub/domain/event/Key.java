package com.callv2.event.hub.domain.event;

import com.callv2.event.hub.domain.ValueObject;

public record Key(String domain, String entity, String action) implements ValueObject {

    public Key {
        domain = domain.trim().toLowerCase();
        entity = entity.trim().toLowerCase();
        action = action.trim().toLowerCase();
    }

    public static Key from(final String domain, final String entity, final String action) {
        return new Key(domain, entity, action);
    }

    public String qualifiedName() {
        return domain() + "." + entity() + "." + action();
    }

}
