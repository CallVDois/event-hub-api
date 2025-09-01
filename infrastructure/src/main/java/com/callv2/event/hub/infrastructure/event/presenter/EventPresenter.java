package com.callv2.event.hub.infrastructure.event.presenter;

import java.util.Set;
import java.util.stream.Collectors;

import com.callv2.event.hub.application.event.retrieve.get.GetEventOutput;
import com.callv2.event.hub.application.event.retrieve.list.ListEventOutput;
import com.callv2.event.hub.infrastructure.event.model.EventEntity;
import com.callv2.event.hub.infrastructure.event.model.GetEventResponse;
import com.callv2.event.hub.infrastructure.event.model.ListEventResponse;

public interface EventPresenter {

    static GetEventResponse present(final GetEventOutput output) {
        return new GetEventResponse(
                output.id(),
                output.domain(),
                output.entity(),
                output.action(),
                output.service(),
                output.version(),
                output.occurredAt(),
                presentGetEventOutputEventEntity(output.relatedEntities()),
                output.data());

    }

    static EventEntity present(final GetEventOutput.EventEntity output) {
        return new EventEntity(output.type(), output.id());
    }

    static Set<EventEntity> presentGetEventOutputEventEntity(final Set<GetEventOutput.EventEntity> outputs) {
        return outputs == null ? Set.of() : outputs.stream().map(EventPresenter::present).collect(Collectors.toSet());
    }

    static ListEventResponse present(final ListEventOutput output) {
        return new ListEventResponse(
                output.id(),
                output.domain(),
                output.entity(),
                output.action(),
                output.service(),
                output.version(),
                output.occurredAt(),
                presentListEventOutputEventEntity(output.relatedEntities()));
    }

    static EventEntity present(final ListEventOutput.EventEntity output) {
        return new EventEntity(output.type(), output.id());
    }

    static Set<EventEntity> presentListEventOutputEventEntity(final Set<ListEventOutput.EventEntity> outputs) {
        return outputs == null ? Set.of() : outputs.stream().map(EventPresenter::present).collect(Collectors.toSet());
    }

}
