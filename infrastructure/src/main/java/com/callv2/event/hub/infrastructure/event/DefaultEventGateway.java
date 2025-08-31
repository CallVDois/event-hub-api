package com.callv2.event.hub.infrastructure.event;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.callv2.event.hub.domain.event.Event;
import com.callv2.event.hub.domain.event.EventGateway;
import com.callv2.event.hub.domain.pagination.Page;
import com.callv2.event.hub.domain.pagination.SearchQuery;
import com.callv2.event.hub.infrastructure.event.persistence.EventMongoEntity;
import com.callv2.event.hub.infrastructure.event.persistence.EventMongoRepository;
import com.callv2.event.hub.infrastructure.filter.FilterService;
import com.callv2.event.hub.infrastructure.filter.adapter.QueryAdapter;

@Component
public class DefaultEventGateway implements EventGateway {

    private final FilterService filterService;
    private final MongoTemplate mongoTemplate;
    private final EventMongoRepository eventRepository;

    public DefaultEventGateway(
            final EventMongoRepository eventRepository,
            final MongoTemplate mongoTemplate,
            final FilterService filterService) {
        this.eventRepository = eventRepository;
        this.mongoTemplate = mongoTemplate;
        this.filterService = filterService;
    }

    @Override
    public void register(final Event event) {
        this.eventRepository.save(EventMongoEntity.fromDomain(event));
    }

    @Override
    public Page<Event> findAll(final SearchQuery searchQuery) {

        final var query = filterService.buildQuery(
                EventMongoEntity.class,
                searchQuery.filterMethod(),
                searchQuery.filters());

        final var pageable = QueryAdapter.of(searchQuery.pagination());

        final long total = mongoTemplate.count(query, EventMongoEntity.class);
        final List<EventMongoEntity> content = mongoTemplate.find(query.with(pageable), EventMongoEntity.class);

        final var pageResult = new PageImpl<>(content, pageable, total);

        return new Page<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalPages(),
                pageResult.getTotalElements(),
                pageResult.map(EventMongoEntity::toDomain).toList());

    }

}
