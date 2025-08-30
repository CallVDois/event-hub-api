package com.callv2.event.hub.application;

public abstract class UseCase<I, O> {

    public abstract O execute(I input);

}
