package com.callv2.event.hub.infrastructure.filter;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.callv2.event.hub.domain.pagination.Filter;
import com.callv2.event.hub.infrastructure.converter.Caster;
import com.callv2.event.hub.infrastructure.reflection.ReflectionUtils;

@Component
public class Equals extends CriteriaFilter {

    public Equals(final Caster caster) {
        super(caster);
    }

    @Override
    public Filter.Type filterType() {
        return Filter.Type.EQUALS;
    }

    @Override
    public <E> Criteria buildCriteria(final Class<E> entityClass, final Filter filter) {
        final Object value = cast(filter.value(), ReflectionUtils.getComplexFieldClass(entityClass, filter.field()));
        return Criteria.where(filter.field()).is(value);
    }

}
