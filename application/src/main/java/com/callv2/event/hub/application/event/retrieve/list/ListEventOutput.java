package com.callv2.event.hub.application.event.retrieve.list;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.callv2.event.hub.domain.event.Event;

public record ListEventOutput(String id,
        String domain,
        String entity,
        String action,
        String service,
        String version,
        Instant occurredAt,
        Set<EventEntity> relatedEntities) {

    public static ListEventOutput from(final Event event) {
        return new ListEventOutput(
                event.getId().getStringValue(),
                event.getKey().domain(),
                event.getKey().entity(),
                event.getKey().action(),
                event.getSource().service(),
                event.getSource().version(),
                event.getOccurredAt(),
                EventEntity.from(event.getRelatedEntities()));
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
