package com.callv2.event.hub.domain.event.old;

import java.io.Serializable;
import java.time.Instant;

public interface EventOld<D extends Serializable> extends Serializable {

    String id();

    String name();

    String source();

    Instant occurredAt();

    D data();

}
