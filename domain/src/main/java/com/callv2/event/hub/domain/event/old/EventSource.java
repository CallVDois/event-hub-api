package com.callv2.event.hub.domain.event.old;

import java.util.Optional;

@FunctionalInterface
public interface EventSource {

    Optional<EventOld<?>> nextEvent();

}
