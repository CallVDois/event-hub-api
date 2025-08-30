package com.callv2.event.hub.infrastructure.event.adapter;

import java.util.Set;
import java.util.stream.Collectors;

import com.callv2.event.hub.application.event.register.RegisterEventInput;
import com.callv2.event.hub.infrastructure.event.model.RegisterEventMessage;

public interface EventAdapter {

    static RegisterEventInput adapt(final RegisterEventMessage message) {

        final Set<RegisterEventInput.Entity> relatedEntities = message.relatedEntities() == null ? Set.of()
                : message.relatedEntities().stream()
                        .map(entity -> RegisterEventInput.Entity.from(entity.type(), entity.id()))
                        .collect(Collectors.toSet());

        return new RegisterEventInput(
                message.domain(),
                message.entity(),
                message.action(),
                message.service(),
                message.version(),
                message.occurredAt(),
                relatedEntities,
                message.data());

    }

}
