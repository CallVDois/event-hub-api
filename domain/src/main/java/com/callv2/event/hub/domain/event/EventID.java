package com.callv2.event.hub.domain.event;

import java.util.UUID;

import com.callv2.event.hub.domain.Identifier;

public class EventID extends Identifier<String> {

    public EventID(final String value) {
        super(value);
    }

    public static EventID unique() {
        return new EventID(UUID.randomUUID().toString());
    }

    public static EventID of(final String id) {
        return new EventID(id);
    }

    @Override
    public String getStringValue() {
        return getValue();
    }

}
