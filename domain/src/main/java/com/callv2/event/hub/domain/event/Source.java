package com.callv2.event.hub.domain.event;

import com.callv2.event.hub.domain.ValueObject;

public record Source(String domain, String service, String version) implements ValueObject {

    public Source {
        domain = domain.trim().toLowerCase();
        service = service.trim().toLowerCase();
        version = version.trim().toLowerCase();
    }

    public static Source from(final String domain, final String service, final String version) {
        return new Source(domain, service, version);
    }

}
