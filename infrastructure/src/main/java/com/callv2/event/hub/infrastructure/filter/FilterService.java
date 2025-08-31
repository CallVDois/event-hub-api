package com.callv2.event.hub.infrastructure.filter;

import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.callv2.event.hub.domain.pagination.Filter;

@Component
public class FilterService {

    private final List<CriteriaFilter> filters;

    public FilterService(final List<CriteriaFilter> filters) {
        this.filters = List.copyOf(Objects.requireNonNull(filters));
    }

    public <E> Query buildQuery(
            final Class<E> entityClass,
            final Filter.Operator filterMethod,
            final List<Filter> filters) {

        if (filterMethod.equals(Filter.Operator.AND))
            return andSpecifications(buildCriterias(entityClass, filters));

        if (filterMethod.equals(Filter.Operator.OR))
            return orSpecifications(buildCriterias(entityClass, filters));

        return andSpecifications(buildCriterias(entityClass, filters));
    }

    private <E> List<Criteria> buildCriterias(final Class<E> entityClass, final List<Filter> filters) {
        if (filters == null)
            return List.of();

        return filters.stream()
                .map(filter -> buildCriteria(entityClass, filter))
                .toList();
    }

    private <E> Criteria buildCriteria(final Class<E> entityClass, final Filter filter) {
        final var criteriaFilter = filters.stream()
                .filter(f -> f.filterType().equals(filter.type()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Filter not found"));

        return criteriaFilter.buildCriteria(entityClass, filter);
    }

    private static Query orSpecifications(final List<Criteria> criterias) {
        return new Query(new Criteria().orOperator(criterias));
    }

    private static Query andSpecifications(final List<Criteria> criterias) {
        return new Query(new Criteria().andOperator(criterias));
    }

}
