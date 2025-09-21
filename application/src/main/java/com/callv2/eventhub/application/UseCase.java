package com.callv2.eventhub.application;

public abstract class UseCase<I, O> {

    public abstract O execute(I input);

}
