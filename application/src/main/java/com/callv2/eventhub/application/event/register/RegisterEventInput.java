package com.callv2.eventhub.application.event.register;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public record RegisterEventInput(
        String domain,
        String entity,
        String action,
        String service,
        String version,
        Instant occurredAt,
        Set<RegisterEventInput.Entity> relatedEntities,
        Map<String, Object> data) {

    public RegisterEventInput {
        relatedEntities = relatedEntities == null ? Set.of() : Set.copyOf(relatedEntities);
    }

    public record Entity(String type, String id) {

        public static Entity from(final String type, final String id) {
            return new Entity(type, id);
        }

    }

}
