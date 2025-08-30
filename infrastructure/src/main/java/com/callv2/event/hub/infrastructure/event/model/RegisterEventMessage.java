package com.callv2.event.hub.infrastructure.event.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

public record RegisterEventMessage(
        String domain,
        String entity,
        String action,
        String service,
        String version,
        Instant occurredAt,
        Set<EventEntity> relatedEntities,
        Map<String, Object> data) implements Serializable {

}
