package com.callv2.event.hub.application.event.retrieve.list;

import com.callv2.event.hub.application.UseCase;
import com.callv2.event.hub.domain.pagination.Page;
import com.callv2.event.hub.domain.pagination.SearchQuery;

public abstract class ListEventUseCase extends UseCase<SearchQuery, Page<ListEventOutput>> {

}
