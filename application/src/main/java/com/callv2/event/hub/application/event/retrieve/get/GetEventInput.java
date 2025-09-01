package com.callv2.event.hub.application.event.retrieve.get;

public record GetEventInput(String id) {

    public static GetEventInput from(final String id) {
        return new GetEventInput(id);
    }

}
