package com.callv2.event.hub.domain.event;

import com.callv2.event.hub.domain.pagination.Page;
import com.callv2.event.hub.domain.pagination.SearchQuery;

public interface EventGateway {

    void register(Event event);

    Page<Event> findAll(SearchQuery searchQuery);

    Page<Event> findAllRelatedToEntity(EventEntity entity, final SearchQuery searchQuery);

}
