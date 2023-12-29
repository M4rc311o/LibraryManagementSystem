package org.but.feec.bds.exceptions;

public class StructureViolationException extends Throwable {
    public StructureViolationException() {}

    public StructureViolationException(String message) {
        super(message);
    }

    public StructureViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public StructureViolationException(Throwable cause) {
        super(cause);
    }

    public StructureViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
