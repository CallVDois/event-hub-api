package com.callv2.event.hub.domain.event.old;

import java.io.Serializable;

public interface EventHandler<D extends Serializable> {

    String eventName();

    void handle(EventOld<D> event);

}
