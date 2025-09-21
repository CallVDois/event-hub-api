package com.callv2.eventhub.infrastructure.event.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

public record EventPublishedMessage(
        String domain,
        String entity,
        String action,
        String service,
        String version,
        Instant occurredAt,
        Set<EventEntity> relatedEntities,
        Map<String, Object> data) implements Serializable {

    public record EventEntity(String type, String id) implements Serializable {

    }

}
