package com.callv2.event.hub.application.event.retrieve.get;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.callv2.event.hub.domain.event.Event;

public record GetEventOutput(
        String id,
        String domain,
        String entity,
        String action,
        String service,
        String version,
        Instant occurredAt,
        Set<EventEntity> relatedEntities,
        Map<String, Object> data) {

    public static GetEventOutput from(final Event event) {
        return new GetEventOutput(
                event.getId().getStringValue(),
                event.getKey().domain(),
                event.getKey().entity(),
                event.getKey().action(),
                event.getSource().service(),
                event.getSource().version(),
                event.getOccurredAt(),
                EventEntity.from(event.getRelatedEntities()),
                event.getData());
    }

    public record EventEntity(String type, String id) {

        public static EventEntity from(final com.callv2.event.hub.domain.event.EventEntity eventEntity) {
            return new EventEntity(eventEntity.type(), eventEntity.domain());
        }

        public static Set<EventEntity> from(final Set<com.callv2.event.hub.domain.event.EventEntity> eventEntities) {
            return eventEntities == null ? Set.of()
                    : eventEntities
                            .stream()
                            .map(EventEntity::from)
                            .collect(Collectors.toSet());
        }

    }

}
