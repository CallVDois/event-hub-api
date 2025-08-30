package com.callv2.event.hub.infrastructure.messaging.producer;

@FunctionalInterface
public interface Producer<T> {

    void send(T data);

}
