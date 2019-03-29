package io.github.pw2.horarioservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.pw2.horarioservice.exceptions.DiaLetivoRepetidoException;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class HorarioAcademico implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long codigoCurso;

    @Column(nullable = false)
    private Integer numeroPeriodo;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "horarioAcademico")
    private List<DiaLetivo> diasLetivos;

    {
        this.diasLetivos = new ArrayList<>();
    }

    public void setDiasLetivos(@NotNull final List<DiaLetivo> diasLetivos) {

        // Verifica se existe algum dia letivo com o mesmo dia da semana e turno
        diasLetivos.forEach(diaLetivo -> {
            if (verificaDiaLetivoRepetido(diaLetivo))
                throw new DiaLetivoRepetidoException("Um dia letivo com o mesmo dia da semane e turno " +
                        "ja foi definido para este Horario academico. DiaLetivo informado: " + diaLetivo);
        });

        this.diasLetivos = diasLetivos;
    }

    /**
     * Adiciona um DiaLetivo a lista de diasLetivos da classe
     *
     * @param diaLetivo Objeto do tipo DiaLetivo que sera adicionado
     * @throws DiaLetivoRepetidoException Caso ja exista um DiaLetivo com o mesmo
     *                                    turno e dia da semana na lista de diasLetivos deste HorarioAcademico
     */
    public void addDiaLetivo(@NotNull final DiaLetivo diaLetivo) {

        // Verifica se ja existe um dia letivo com o mesmo dia da semana e turno
        if (verificaDiaLetivoRepetido(diaLetivo))
            throw new DiaLetivoRepetidoException("Um dia letivo com o mesmo dia da semane e turno " +
                    "ja foi definido para este Horario academico. DiaLetivo informado: " + diaLetivo);

        this.diasLetivos.add(diaLetivo);
    }

    /**
     * Verifica se existe algum DiaLetivo na lista de diasLetivos com o mesmo Dia do
     * diaLetivo passado no parametro
     *
     * @param diaLetivo Objeto do tipo DiaLetivo que sera verificado na lista de dias Letivos
     * @return True se exite algum diaLetivo com o mesmo Dia, false caso nao haja
     */
    private Boolean verificaDiaLetivoRepetido(@NotNull final DiaLetivo diaLetivo) {
        return this.getDiasLetivos()
                .stream()
                .anyMatch(dia -> dia.getDia().equals(diaLetivo.getDia()));
    }

    public boolean validate() {
        return this.numeroPeriodo != null && this.numeroPeriodo >= 0 &&
                this.codigoCurso != null && this.codigoCurso >= 0 &&
                this.diasLetivos != null && !this.diasLetivos.isEmpty() &&
                this.diasLetivos.stream().allMatch(DiaLetivo::validate);
    }

}
