package com.callv2.event.hub.infrastructure.configuration.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.callv2.event.hub.application.event.register.DefaultRegisterEventUseCase;
import com.callv2.event.hub.application.event.register.RegisterEventUseCase;
import com.callv2.event.hub.application.event.retrieve.get.DefaultGetEventUseCase;
import com.callv2.event.hub.application.event.retrieve.get.GetEventUseCase;
import com.callv2.event.hub.application.event.retrieve.list.DefaultListEventUseCase;
import com.callv2.event.hub.application.event.retrieve.list.ListEventUseCase;
import com.callv2.event.hub.domain.event.EventGateway;

@Configuration
public class EventUseCaseConfig {

    private final EventGateway eventGateway;

    public EventUseCaseConfig(final EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @Bean
    RegisterEventUseCase registerEventUseCase() {
        return new DefaultRegisterEventUseCase(eventGateway);
    }

    @Bean
    GetEventUseCase getEventUseCase() {
        return new DefaultGetEventUseCase(eventGateway);
    }

    @Bean
    ListEventUseCase listEventUseCase() {
        return new DefaultListEventUseCase(eventGateway);
    }

}