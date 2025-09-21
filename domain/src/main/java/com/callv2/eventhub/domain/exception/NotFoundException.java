package com.callv2.eventhub.domain.exception;

import java.util.List;

import com.callv2.eventhub.domain.Entity;
import com.callv2.eventhub.domain.Identifier;

public class NotFoundException extends SilentDomainException {

    private NotFoundException(
            final Class<? extends Entity<?>> entityClass,
            final String id) {
        super("[%s] not found.".formatted(entityClass.getSimpleName()),
                List.of(DomainException.Error
                        .with("[%s] with id [%s] not found.".formatted(entityClass.getSimpleName(), id))));
    }

    public static <E extends Entity<I>, I extends Identifier<?>> NotFoundException with(
            final Class<E> entityClass,
            final I id) {
        return new NotFoundException(entityClass, id.getStringValue());
    }

}
