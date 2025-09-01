package com.callv2.eventhub.domain;

public abstract class AggregateRoot<ID extends Identifier<?>> extends Entity<ID> {

    protected AggregateRoot(final ID id) {
        super(id);
    }

}
