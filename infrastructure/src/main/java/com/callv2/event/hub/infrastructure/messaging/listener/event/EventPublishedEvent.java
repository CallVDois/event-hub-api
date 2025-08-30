package com.callv2.event.hub.infrastructure.messaging.listener.event;

import java.util.Map;

public record EventPublishedEvent(
        String entity,
        String action,
        String sourceDomain,
        String sourceService,
        String sourceVersion,
        Map<String, Object> data) {

}
