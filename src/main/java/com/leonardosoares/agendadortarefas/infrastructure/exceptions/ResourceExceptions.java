package com.leonardosoares.agendadortarefas.infrastructure.exceptions;

public class ResourceExceptions extends RuntimeException {
    public ResourceExceptions(String message) {
        super(message);
    }

    public ResourceExceptions(String message, Throwable throwable) {
        super(message, throwable);
    }
}
