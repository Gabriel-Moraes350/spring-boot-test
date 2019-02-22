package com.moraes.agenda.exceptions;

public class NotFoundItemException extends RuntimeException {
    private Long id;

    public NotFoundItemException(Long id) {
        super("Não foi encontrado item para o id: " + id);
        this.id = id;
    }
}
