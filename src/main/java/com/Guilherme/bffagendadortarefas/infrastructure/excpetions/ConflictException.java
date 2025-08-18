package com.Guilherme.bffagendadortarefas.infrastructure.excpetions;

public class ConflictException extends RuntimeException{

    public ConflictException(String mensagem) {
        super(mensagem);
    }

    public ConflictException(String message, Throwable cause) {
        super(message);
    }
}
