package com.callv2.event.hub.infrastructure.filter;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.callv2.event.hub.domain.pagination.Filter;
import com.callv2.event.hub.infrastructure.converter.Caster;
import com.callv2.event.hub.infrastructure.reflection.ReflectionUtils;

@Component
public class Between extends CriteriaFilter {

    public Between(final Caster caster) {
        super(caster);
    }

    @Override
    public Filter.Type filterType() {
        return Filter.Type.BETWEEN;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public <E> Criteria buildCriteria(final Class<E> entityClass, final Filter filter) {

        final Object value = cast(
                filter.value(),
                ReflectionUtils.getComplexFieldClass(entityClass, filter.field()));

        final Object valueToCompare = cast(
                filter.valueToCompare(),
                ReflectionUtils.getComplexFieldClass(entityClass, filter.field()));

        if (!(value instanceof Comparable) || !(valueToCompare instanceof Comparable)) {
            throw new IllegalArgumentException("Both values must be Comparable");
        }

        final Object min = ((Comparable) value).compareTo(valueToCompare) <= 0 ? value : valueToCompare;
        final Object max = ((Comparable) value).compareTo(valueToCompare) > 0 ? value : valueToCompare;

        return Criteria.where(filter.field()).gte(min).lte(max);

    }

}
