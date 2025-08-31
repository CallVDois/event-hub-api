package com.callv2.event.hub.infrastructure.messaging.listener.event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.callv2.event.hub.application.event.register.RegisterEventUseCase;
import com.callv2.event.hub.infrastructure.event.adapter.EventAdapter;
import com.callv2.event.hub.infrastructure.event.model.EventPublishedMessage;
import com.callv2.event.hub.infrastructure.messaging.listener.Listener;

@Component
public class EventPublishedListener implements Listener<EventPublishedMessage> {

    private final RegisterEventUseCase registerEventUseCase;

    public EventPublishedListener(final RegisterEventUseCase registerEventUseCase) {
        this.registerEventUseCase = registerEventUseCase;
    }

    @Override
    @RabbitListener(queues = "eventhub.event.published.queue")
    public void handle(final EventPublishedMessage message) {
        this.registerEventUseCase.execute(EventAdapter.adapt(message));
    }

}
