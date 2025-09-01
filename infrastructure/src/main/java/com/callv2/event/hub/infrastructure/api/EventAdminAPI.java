package com.callv2.event.hub.infrastructure.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.callv2.event.hub.domain.pagination.Filter;
import com.callv2.event.hub.domain.pagination.Page;
import com.callv2.event.hub.domain.pagination.Pagination;
import com.callv2.event.hub.infrastructure.event.model.GetEventResponse;
import com.callv2.event.hub.infrastructure.event.model.ListEventResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Events Admin")
@RequestMapping("admin/events")
public interface EventAdminAPI {

    @Operation(summary = "Retrive a Event", description = "This method retrive a event", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Event retrieved successfully", content = @Content(schema = @Schema(implementation = GetEventResponse.class)))
    @ApiResponse(responseCode = "404", description = "Event not found", content = @Content(schema = @Schema(implementation = Void.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ApiError.class)))
    @GetMapping("{id}")
    ResponseEntity<GetEventResponse> getById(@PathVariable(name = "id") String id);

    @Operation(summary = "List events", description = "This method list events", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Events listed successfully", content = @Content(schema = @Schema(implementation = Page.class, subTypes = {
            ListEventResponse.class })))
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ApiError.class)))
    @GetMapping
    ResponseEntity<Page<ListEventResponse>> list(
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "orderField", required = false, defaultValue = "createdAt") String orderField,
            @RequestParam(name = "orderDirection", required = false, defaultValue = "DESC") Pagination.Order.Direction orderDirection,
            @RequestParam(name = "filterOperator", required = false, defaultValue = "AND") Filter.Operator filterOperator,
            @RequestParam(name = "filters", required = false) List<String> filters);

}
