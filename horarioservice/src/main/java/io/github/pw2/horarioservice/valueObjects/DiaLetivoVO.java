package io.github.pw2.horarioservice.valueObjects;

import com.google.common.collect.ImmutableList;
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
@Builder
public final class DiaLetivoVO implements Serializable {

    private DayOfWeek dia;
    private List<AulaVO> aulas;

    {
        this.aulas = new ArrayList<>();
    }

    public List<AulaVO> getAulas() {
        return ImmutableList.copyOf(this.aulas);
    }

    public void addAula(AulaVO aula) {
        this.aulas.add(aula);
    }

    public void addAllAulas(List<AulaVO> aulasToAdd) {
        this.aulas.addAll(aulasToAdd);
    }

}