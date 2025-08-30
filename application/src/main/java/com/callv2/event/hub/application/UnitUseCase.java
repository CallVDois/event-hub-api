package com.callv2.event.hub.application;

public abstract class UnitUseCase<I> {

    public abstract void execute(I input);

}
