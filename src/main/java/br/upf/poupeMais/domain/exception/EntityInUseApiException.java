package br.upf.poupeMais.domain.exception;

public class EntityInUseApiException extends RuntimeException {

    public EntityInUseApiException(String message) {
        super(message);
    }
}
