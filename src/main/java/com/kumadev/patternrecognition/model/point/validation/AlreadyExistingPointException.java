package com.kumadev.patternrecognition.model.point.validation;

public class AlreadyExistingPointException extends RuntimeException {
    public AlreadyExistingPointException(String message) {
        super(message);
    }
}
