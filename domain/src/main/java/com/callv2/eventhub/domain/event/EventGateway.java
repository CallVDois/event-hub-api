package com.callv2.eventhub.domain.event;

import java.util.Optional;

import com.callv2.eventhub.domain.pagination.Page;
import com.callv2.eventhub.domain.pagination.SearchQuery;

public interface EventGateway {

    Optional<Event> findById(EventID id);

    void register(Event event);

    Page<Event> findAll(SearchQuery searchQuery);

}
