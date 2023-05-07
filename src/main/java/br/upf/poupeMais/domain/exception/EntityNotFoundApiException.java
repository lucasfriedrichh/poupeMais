package br.upf.poupeMais.domain.exception;


public class EntityNotFoundApiException extends RuntimeException {

    public EntityNotFoundApiException(String message) {
        super(message);
    }
}
