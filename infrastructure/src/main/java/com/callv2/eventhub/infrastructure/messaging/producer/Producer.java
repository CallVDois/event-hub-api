package com.callv2.eventhub.infrastructure.messaging.producer;

@FunctionalInterface
public interface Producer<T> {

    void send(T data);

}
