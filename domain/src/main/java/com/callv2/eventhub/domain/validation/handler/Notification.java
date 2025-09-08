package com.callv2.eventhub.domain.validation.handler;

import java.util.ArrayList;
import java.util.List;

import com.callv2.eventhub.domain.exception.DomainException;
import com.callv2.eventhub.domain.validation.ValidationError;
import com.callv2.eventhub.domain.validation.ValidationHandler;

public class Notification implements ValidationHandler {

    private final List<ValidationError> errors;

    private Notification(final List<ValidationError> errors) {
        this.errors = errors == null ? new ArrayList<>() : new ArrayList<>(errors);
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Throwable t) {
        return new Notification(new ArrayList<>()).append(new ValidationError(t.getMessage()));
    }

    public static Notification create(final ValidationError error) {
        return new Notification(new ArrayList<>()).append(error);
    }

    @Override
    public Notification append(final ValidationError error) {
        this.errors.add(error);
        return this;
    }

    @Override
    public Notification append(final ValidationHandler handler) {
        this.errors.addAll(handler.getErrors());
        return this;
    }

    @Override
    public <T> T valdiate(final Validation<T> validation) {

        try {
            return validation.validate();
        } catch (DomainException e) {

            final List<ValidationError> errors = e.getErrors() == null ? List.of()
                    : e.getErrors().stream().map(ValidationError::fromDomainError).toList();

            this.errors.addAll(new ArrayList<>(errors));
        } catch (Throwable e) {
            this.errors.add(new ValidationError(e.getMessage()));
        }

        return null;
    }

    @Override
    public List<ValidationError> getErrors() {
        return List.copyOf(this.errors);
    }

}
