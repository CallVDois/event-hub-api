package com.callv2.event.hub.domain;

import com.callv2.event.hub.domain.validation.ValidationHandler;

@FunctionalInterface
public interface Validatable {

    void validate(ValidationHandler aHandler);

}
