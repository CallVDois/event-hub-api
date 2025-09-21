package com.callv2.eventhub.domain;

import com.callv2.eventhub.domain.validation.ValidationHandler;

@FunctionalInterface
public interface Validatable {

    void validate(ValidationHandler aHandler);

}
