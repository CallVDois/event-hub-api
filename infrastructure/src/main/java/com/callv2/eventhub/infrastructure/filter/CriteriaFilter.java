package com.callv2.eventhub.infrastructure.filter;

import java.util.Objects;

import org.springframework.data.mongodb.core.query.Criteria;

import com.callv2.eventhub.domain.pagination.Filter;
import com.callv2.eventhub.infrastructure.converter.Caster;

public abstract class CriteriaFilter {

    private final Caster caster;

    protected CriteriaFilter(final Caster caster) {
        this.caster = Objects.requireNonNull(caster);
    }

    abstract Filter.Type filterType();

    abstract <E> Criteria buildCriteria(Class<E> entityClass, Filter filter);

    protected <T> T cast(final Object value, final Class<T> clazz) {
        return caster.cast(value, clazz);
    }

}
