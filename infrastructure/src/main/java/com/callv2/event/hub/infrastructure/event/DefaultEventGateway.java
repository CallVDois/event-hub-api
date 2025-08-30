package com.callv2.event.hub.infrastructure.event;

import org.springframework.stereotype.Component;

import com.callv2.event.hub.domain.event.Event;
import com.callv2.event.hub.domain.event.EventEntity;
import com.callv2.event.hub.domain.event.EventGateway;
import com.callv2.event.hub.domain.pagination.Page;
import com.callv2.event.hub.domain.pagination.SearchQuery;
import com.callv2.event.hub.infrastructure.event.persistence.EventMongoEntity;
import com.callv2.event.hub.infrastructure.event.persistence.EventMongoRepository;

@Component
public class DefaultEventGateway implements EventGateway {

    private final EventMongoRepository eventRepository;

    public DefaultEventGateway(EventMongoRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void register(final Event event) {
        this.eventRepository.save(EventMongoEntity.fromDomain(event));
    }

    @Override
    public Page<Event> findAll(final SearchQuery searchQuery) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Page<Event> findAllRelatedToEntity(final EventEntity entity, final SearchQuery searchQuery) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllRelatedToEntity'");
    }

}
