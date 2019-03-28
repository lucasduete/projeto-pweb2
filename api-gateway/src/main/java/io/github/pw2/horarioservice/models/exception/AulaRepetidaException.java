package io.github.pw2.horarioservice.models.exception;

import io.github.pw2.horarioservice.models.Aula;

public final class AulaRepetidaException extends Exception {

    private final Aula aula;

    public AulaRepetidaException(final Aula aula) {
        super();
        this.aula = aula;
    }

    public Aula getAula() {
        return aula;
    }

}
