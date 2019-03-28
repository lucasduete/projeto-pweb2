package io.github.pw2.horarioservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.ifpb.pw2.apigateway.valueObjects.AulaVO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class Aula implements Serializable, Cloneable {


    private Long id;


    private Integer numeroAula;


    private TipoTurno turno;
    private LocalTime horaInicio;


    private LocalTime horaFim;

    private String matriculaProfessor;

    private Long codigoDisciplina;

    private Long codigoAmbiente;


    private DiaLetivo diaLetivo;

    public boolean equalsAula(Aula aula) {
        return this.getTurno().equals(aula.getTurno()) && this.getNumeroAula().equals(aula.getNumeroAula());
    }

    public boolean equalsHoraInicio(Aula aula) {
        return this.getHoraInicio().equals(aula.getHoraInicio()) && !this.getNumeroAula().equals(aula.getNumeroAula());
    }

    public boolean equalsHoraInicioAnterior(Aula aula) {
        return this.getHoraInicio().isBefore(aula.getHoraFim()) && !this.getNumeroAula().equals(aula.getNumeroAula()) && this.getTurno().equals(aula.getTurno()) && this.getNumeroAula() > aula.getNumeroAula();
    }

    public boolean equalsHoraFimPosteriorAoInicio(Aula aula) {
        return this.getHoraFim().isAfter(aula.getHoraInicio()) && !this.getNumeroAula().equals(aula.getNumeroAula()) && this.getTurno().equals(aula.getTurno()) && this.getNumeroAula() < aula.getNumeroAula();
    }

    public boolean equalsHoraFim(Aula aula) {
        return this.getHoraFim().equals(aula.getHoraFim()) && !this.getNumeroAula().equals(aula.getNumeroAula());
    }

    public boolean equalsHoras() {
        return this.getHoraInicio().equals(this.getHoraFim());
    }

    public boolean equalsHorasInicioEFim() {
        return this.getHoraInicio().isAfter(this.getHoraFim());
    }

    public boolean equalsNumeroTurno(final Integer numeroAula, final String turno) {
        return this.getTurno().equals(TipoTurno.of(turno)) && this.getNumeroAula().equals(numeroAula);
    }

    public boolean validate() {
        return this.numeroAula == null || this.numeroAula <= 0 ||
                this.turno == null || this.horaInicio == null ||
                this.horaFim == null || this.codigoAmbiente == null ||
                this.codigoAmbiente <= 0 || this.codigoDisciplina == null ||
                this.codigoDisciplina <= 0 || this.matriculaProfessor == null ||
                this.matriculaProfessor.isEmpty();
    }

}
