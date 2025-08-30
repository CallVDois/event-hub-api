package com.callv2.event.hub.infrastructure.configuration.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.callv2.event.hub.application.event.register.DefaultRegisterEventUseCase;
import com.callv2.event.hub.application.event.register.RegisterEventUseCase;
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

}