package com.callv2.eventhub.infrastructure.event.persistence;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.callv2.eventhub.domain.event.Event;
import com.callv2.eventhub.domain.event.EventEntity;
import com.callv2.eventhub.domain.event.EventID;
import com.callv2.eventhub.domain.event.Key;
import com.callv2.eventhub.domain.event.Source;

@Document(collection = "events")
public class EventMongoEntity {

    @MongoId
    private String id;
    private String domain;
    private String entity;
    private String action;
    private String service;
    private String version;
    private Instant occurredAt;
    private Instant ingestedAt;
    private Set<EventEntityMongo> relatedEntities;
    private Map<String, Object> data;

    public EventMongoEntity() {
    }

    public EventMongoEntity(
            String id,
            String domain,
            String entity,
            String action,
            String service,
            String version,
            Instant occurredAt,
            Instant ingestedAt,
            Set<EventEntityMongo> relatedEntities,
            Map<String, Object> data) {
        this.id = id;
        this.domain = domain;
        this.entity = entity;
        this.action = action;
        this.service = service;
        this.version = version;
        this.occurredAt = occurredAt;
        this.ingestedAt = ingestedAt;
        this.relatedEntities = relatedEntities == null ? new HashSet<>() : new HashSet<>(relatedEntities);
        this.data = new HashMap<>(data);
    }

    public static EventMongoEntity fromDomain(final Event event) {

        final Set<EventEntityMongo> relatedEntities = event
                .getRelatedEntities() == null ? new HashSet<>()
                        : event
                                .getRelatedEntities()
                                .stream()
                                .map(EventEntityMongo::fromDomain)
                                .collect(Collectors.toSet());

        return new EventMongoEntity(
                event.getId().getValue(),
                event.getKey().domain(),
                event.getKey().entity(),
                event.getKey().action(),
                event.getSource().service(),
                event.getSource().version(),
                event.getOccurredAt(),
                event.getIngestedAt(),
                relatedEntities,
                event.getData());
    }

    public Event toDomain() {

        final Set<EventEntity> relatedEntities = this.relatedEntities.stream()
                .map(entity -> entity.toDomain(this.domain))
                .collect(Collectors.toSet());

        return Event.with(
                EventID.of(id),
                Key.from(domain, entity, action),
                Source.from(domain, service, version),
                occurredAt,
                ingestedAt,
                relatedEntities,
                data);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(Instant occurredAt) {
        this.occurredAt = occurredAt;
    }

    public Instant getIngestedAt() {
        return ingestedAt;
    }

    public void setIngestedAt(Instant ingestedAt) {
        this.ingestedAt = ingestedAt;
    }

    public Set<EventEntityMongo> getRelatedEntities() {
        return relatedEntities;
    }

    public void setRelatedEntities(Set<EventEntityMongo> relatedEntities) {
        this.relatedEntities = relatedEntities;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
