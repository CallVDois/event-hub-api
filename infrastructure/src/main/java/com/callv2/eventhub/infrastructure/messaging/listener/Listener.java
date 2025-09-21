package com.callv2.eventhub.infrastructure.messaging.listener;

@FunctionalInterface
public interface Listener<T> {

    void handle(T data);

}
