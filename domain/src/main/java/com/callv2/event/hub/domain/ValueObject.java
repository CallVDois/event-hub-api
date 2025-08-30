package com.callv2.event.hub.domain;

import com.callv2.event.hub.domain.validation.ValidationHandler;

public interface ValueObject extends Validatable {

    @Override
    default void validate(ValidationHandler aHandler) {
    };

}
