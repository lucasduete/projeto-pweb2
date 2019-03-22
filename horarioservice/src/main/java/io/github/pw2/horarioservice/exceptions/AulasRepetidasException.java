package io.github.pw2.horarioservice.exceptions;

import com.google.common.collect.ImmutableList;
import io.github.pw2.horarioservice.models.Aula;

import java.util.List;

public final class AulasRepetidasException extends Exception {

    private final List<Aula> aulas;

    public AulasRepetidasException(final List<Aula> aulas) {
        super();
        this.aulas = aulas;
    }

    public List<Aula> getAulas() {
        return ImmutableList.copyOf(this.aulas);
    }

}
