package com.callv2.eventhub.domain;

import com.callv2.eventhub.domain.validation.ValidationHandler;

public interface ValueObject extends Validatable {

    @Override
    default void validate(ValidationHandler aHandler) {
    };

}
