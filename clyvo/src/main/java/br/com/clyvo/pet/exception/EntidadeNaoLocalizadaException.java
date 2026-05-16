package br.com.clyvo.pet.exception;

public class EntidadeNaoLocalizadaException extends RuntimeException {

    public EntidadeNaoLocalizadaException(String mensagem) {
        super(mensagem);
    }
}