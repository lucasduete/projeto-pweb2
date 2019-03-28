package io.github.pw2.horarioservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.pw2.horarioservice.models.exception.AulaNaoCadastradaException;
import io.github.pw2.horarioservice.models.exception.AulaRepetidaException;
import io.github.pw2.horarioservice.models.exception.AulasRepetidasException;
import lombok.*;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class DiaLetivo implements Serializable, Cloneable {

    private Long id;

    private DayOfWeek dia;

    private List<Aula> aulas;

    @JsonIgnore
    private HorarioAcademico horarioAcademico;

    {
        this.aulas = new ArrayList<>();
    }

    public void addAula(Aula aula) throws AulaRepetidaException {
        Boolean aulaRepetida = getAulas().stream().anyMatch(aulaTemp -> aulaTemp.equalsAula(aula));

        if (aulaRepetida) throw new AulaRepetidaException(aula);
        else this.aulas.add(aula);
    }

    public void addAllAulas(List<Aula> aulasToAdd) throws AulasRepetidasException {
        List<Aula> aulasRepetidas = new ArrayList<>();

        this.getAulas()
                .forEach(aula -> aulasToAdd
                        .stream()
                        .filter(aulaTemp -> aulaTemp.equalsAula(aula) ||
                                aulaTemp.equalsHoraInicio(aula) ||
                                aulaTemp.equalsHoraFim((aula)) ||
                                aulaTemp.equalsHoras() ||
                                aulaTemp.equalsHorasInicioEFim() ||
                                aulaTemp.equalsHoraInicioAnterior(aula) ||
                                aulaTemp.equalsHoraFimPosteriorAoInicio(aula))
                        .forEach(aulaTemp -> aulasRepetidas.add(aula)));

        if (aulasRepetidas.size() > 0) throw new AulasRepetidasException(aulasRepetidas);
        else this.aulas.addAll(aulasToAdd);
    }

    public void removeAula(Aula aula) throws AulaNaoCadastradaException {
        if (!this.aulas.remove(aula))
            throw new AulaNaoCadastradaException();

    }

    public boolean validate() {
        return this.dia == null ||
                this.aulas == null ||
                this.aulas.isEmpty() ||
                this.aulas.stream().anyMatch(aula -> !aula.validate());
    }

}