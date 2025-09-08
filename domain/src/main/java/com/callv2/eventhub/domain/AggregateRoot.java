package com.callv2.eventhub.domain;

public abstract class AggregateRoot<I extends Identifier<?>> extends Entity<I> {

    protected AggregateRoot(final I id) {
        super(id);
    }

}
