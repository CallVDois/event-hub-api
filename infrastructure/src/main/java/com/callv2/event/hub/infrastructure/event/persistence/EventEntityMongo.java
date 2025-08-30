package com.callv2.event.hub.infrastructure.event.persistence;

import com.callv2.event.hub.domain.event.EventEntity;

public record EventEntityMongo(String type, String id) {

    public static EventEntityMongo fromDomain(final EventEntity eventEntity) {
        return new EventEntityMongo(eventEntity.type(), eventEntity.id());
    }

    public EventEntity toDomain(final String domain) {
        return EventEntity.from(domain, type, id);
    }

}
