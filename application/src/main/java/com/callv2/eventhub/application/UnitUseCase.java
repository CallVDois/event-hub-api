package com.callv2.eventhub.application;

public abstract class UnitUseCase<I> {

    public abstract void execute(I input);

}
