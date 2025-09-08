package com.callv2.eventhub.infrastructure.event.model;

import java.time.Instant;
import java.util.Set;

public record ListEventResponse(
        String id,
        String domain,
        String entity,
        String action,
        String service,
        String version,
        Instant occurredAt,
        Set<EventEntity> relatedEntities) {

}
