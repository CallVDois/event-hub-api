package com.callv2.event.hub.application.event.retrieve.list;

import com.callv2.event.hub.domain.event.EventGateway;
import com.callv2.event.hub.domain.pagination.Page;
import com.callv2.event.hub.domain.pagination.SearchQuery;

public class DefaultListEventUseCase extends ListEventUseCase {

    private final EventGateway eventGateway;

    public DefaultListEventUseCase(final EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @Override
    public Page<ListEventOutput> execute(final SearchQuery input) {

        return eventGateway
                .findAll(input)
                .map(ListEventOutput::from);

    }

}
