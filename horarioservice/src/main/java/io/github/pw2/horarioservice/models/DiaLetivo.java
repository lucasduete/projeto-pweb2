package io.github.pw2.horarioservice.models;

import com.fasterxml.jackson.annotation.*;
import io.github.pw2.horarioservice.exceptions.AulaNaoCadastradaException;
import io.github.pw2.horarioservice.exceptions.AulaRepetidaException;
import io.github.pw2.horarioservice.exceptions.AulasRepetidasException;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class DiaLetivo implements Serializable, Cloneable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private DayOfWeek dia;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true, mappedBy = "diaLetivo")
    private List<Aula> aulas;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "horarioacademico_id", nullable = false)
    private HorarioAcademico horarioAcademico;

    @Transient
    private static final Locale LOCALE = Locale.getDefault();

    @Transient
    private static final TextStyle TEXTSTYLE = TextStyle.FULL;

    {
        this.aulas = new ArrayList<>();
    }

    @JsonGetter("dia")
    public String getDiaFromJson() {

        if (this.dia != null) return this.dia.getDisplayName(TEXTSTYLE, LOCALE);
        else return "";
    }

    @JsonSetter("dia")
    public void setDiaFromJson(String dia) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_WEEK, TEXTSTYLE)
                .toFormatter(LOCALE);

        this.dia = formatter.parse(dia, DayOfWeek::from);
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
        return this.dia != null && this.aulas != null
                && !this.aulas.isEmpty() &&
                this.aulas.stream().allMatch(Aula::validate);
    }

}