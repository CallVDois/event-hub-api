package com.callv2.event.hub.infrastructure.messaging.listener;

@FunctionalInterface
public interface Listener<T> {

    void handle(T data);

}
