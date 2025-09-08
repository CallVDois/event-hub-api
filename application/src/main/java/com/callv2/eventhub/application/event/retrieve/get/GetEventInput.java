package com.callv2.eventhub.application.event.retrieve.get;

public record GetEventInput(String id) {

    public static GetEventInput from(final String id) {
        return new GetEventInput(id);
    }

}
