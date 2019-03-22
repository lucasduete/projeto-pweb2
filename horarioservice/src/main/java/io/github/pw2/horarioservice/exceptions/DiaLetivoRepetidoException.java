package io.github.pw2.horarioservice.exceptions;

public class DiaLetivoRepetidoException extends RuntimeException {

    public DiaLetivoRepetidoException() {
        super("Um dia letivo com o mesmo Dia da Semana e Turno ja foi definido");
    }

    public DiaLetivoRepetidoException(String message) {
        super(message);
    }
}
