package com.callv2.eventhub.application.event.retrieve.list;

import com.callv2.eventhub.application.UseCase;
import com.callv2.eventhub.domain.pagination.Page;
import com.callv2.eventhub.domain.pagination.SearchQuery;

public abstract class ListEventUseCase extends UseCase<SearchQuery, Page<ListEventOutput>> {

}
