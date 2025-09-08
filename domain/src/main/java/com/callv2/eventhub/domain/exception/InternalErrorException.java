package com.callv2.eventhub.domain.exception;

public class InternalErrorException extends NoStacktraceException {

    protected InternalErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static InternalErrorException with(final String message, final Throwable cause) {
        return new InternalErrorException(message, cause);
    }

}
