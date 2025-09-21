package com.callv2.eventhub.infrastructure.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.callv2.eventhub.application.event.retrieve.get.GetEventUseCase;
import com.callv2.eventhub.application.event.retrieve.list.ListEventUseCase;
import com.callv2.eventhub.domain.pagination.Filter;
import com.callv2.eventhub.domain.pagination.Page;
import com.callv2.eventhub.domain.pagination.Pagination;
import com.callv2.eventhub.domain.pagination.SearchQuery;
import com.callv2.eventhub.domain.pagination.Filter.Operator;
import com.callv2.eventhub.domain.pagination.Pagination.Order.Direction;
import com.callv2.eventhub.infrastructure.api.EventAdminAPI;
import com.callv2.eventhub.infrastructure.event.adapter.EventAdapter;
import com.callv2.eventhub.infrastructure.event.model.GetEventResponse;
import com.callv2.eventhub.infrastructure.event.model.ListEventResponse;
import com.callv2.eventhub.infrastructure.event.presenter.EventPresenter;
import com.callv2.eventhub.infrastructure.filter.adapter.QueryAdapter;

@RestController
public class EventAdminController implements EventAdminAPI {

    private final GetEventUseCase getEventUseCase;
    private final ListEventUseCase listEventUseCase;

    public EventAdminController(
            final GetEventUseCase getEventUseCase,
            final ListEventUseCase listEventUseCase) {
        this.getEventUseCase = getEventUseCase;
        this.listEventUseCase = listEventUseCase;
    }

    @Override
    public ResponseEntity<GetEventResponse> getById(final String id) {

        final GetEventResponse response = EventPresenter.present(getEventUseCase.execute(EventAdapter.adapt(id)));
        return ResponseEntity.ok(response);

    }

    @Override
    public ResponseEntity<Page<ListEventResponse>> list(
            int page,
            int perPage,
            String orderField,
            Direction orderDirection,
            Operator filterOperator,
            List<String> filters) {

        final List<Filter> searchFilters = filters == null ? List.of()
                : filters
                        .stream()
                        .map(QueryAdapter::of)
                        .toList();

        final SearchQuery query = SearchQuery.of(
                Pagination.of(page, perPage, Pagination.Order.of(orderField, orderDirection)),
                filterOperator,
                searchFilters);

        return ResponseEntity.ok(listEventUseCase.execute(query).map(EventPresenter::present));

    }

}
