package com.callv2.event.hub.application.event.register;

import java.util.Set;
import java.util.stream.Collectors;

import com.callv2.event.hub.domain.event.Event;
import com.callv2.event.hub.domain.event.EventEntity;
import com.callv2.event.hub.domain.event.EventGateway;

public class DefaultRegisterEventUseCase extends RegisterEventUseCase {

    private final EventGateway eventGateway;

    public DefaultRegisterEventUseCase(final EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @Override
    public void execute(final RegisterEventInput input) {

        final String domain = input.domain();
        final Set<EventEntity> relatedEntities = input.relatedEntities()
                .stream()
                .map(entity -> EventEntity.from(domain, entity.type(), entity.id()))
                .collect(Collectors.toSet());

        final Event event = Event.create(
                input.domain(),
                input.entity(),
                input.action(),
                input.service(),
                input.version(),
                input.occurredAt(),
                relatedEntities,
                input.data());

        this.eventGateway.register(event);
    }

}
