package com.callv2.eventhub.domain.event;

import com.callv2.eventhub.domain.ValueObject;
import com.callv2.eventhub.domain.validation.ValidationError;
import com.callv2.eventhub.domain.validation.ValidationHandler;

public record Source(String domain, String service, String version) implements ValueObject {

    public Source {
        domain = domain.trim().toLowerCase();
        service = service.trim().toLowerCase();
        version = version.trim().toLowerCase();
    }

    @Override
    public void validate(final ValidationHandler aHandler) {

        if (domain == null || domain.isBlank())
            aHandler.append(ValidationError.with("domain must not be null or empty"));

        if (service == null || service.isBlank())
            aHandler.append(ValidationError.with("service must not be null or empty"));

        if (version == null || version.isBlank())
            aHandler.append(ValidationError.with("version must not be null or empty"));

    }

    public static Source from(final String domain, final String service, final String version) {
        return new Source(domain, service, version);
    }

}
