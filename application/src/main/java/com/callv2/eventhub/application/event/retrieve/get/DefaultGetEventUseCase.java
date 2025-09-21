package com.callv2.eventhub.application.event.retrieve.get;

import java.util.Objects;

import com.callv2.eventhub.domain.event.Event;
import com.callv2.eventhub.domain.event.EventGateway;
import com.callv2.eventhub.domain.event.EventID;
import com.callv2.eventhub.domain.exception.NotFoundException;

public class DefaultGetEventUseCase extends GetEventUseCase {

    private final EventGateway eventGateway;

    public DefaultGetEventUseCase(final EventGateway eventGateway) {
        this.eventGateway = Objects.requireNonNull(eventGateway);
    }

    @Override
    public GetEventOutput execute(GetEventInput input) {

        final EventID id = EventID.of(input.id());

        return this.eventGateway
                .findById(id)
                .map(GetEventOutput::from)
                .orElseThrow(() -> NotFoundException.with(Event.class, id));

    }

}
