package io.github.pw2.horarioservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.pw2.horarioservice.enums.TipoTurno;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class Aula implements Serializable, Cloneable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer numeroAula;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTurno turno;

    @Column
    private LocalTime horaInicio;

    @Column
    private LocalTime horaFim;

    @Column(nullable = false)
    private String matriculaProfessor;

    @Column(nullable = false)
    private Long codigoDisciplina;

    @Column(nullable = false)
    private String codigoAmbiente;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dialetivo_id", nullable = false)
    private DiaLetivo diaLetivo;

    @JsonSetter("horaInicio")
    public void setHoraInicioJson(String horaInicio) {

        if (horaInicio != null && horaInicio.length() == 5)
            this.horaInicio = LocalTime.parse(horaInicio, DateTimeFormatter.ofPattern("HH:mm"));
    }

    @JsonGetter("horaInicio")
    public String getHoraInicioJson() {

        if (this.horaInicio != null) {
            return this.horaInicio.format(DateTimeFormatter.ofPattern("HH:mm"));
        } else return "";
    }

    @JsonSetter("horaFim")
    public void setHoraFimJson(String horaFim) {

        if (horaFim != null && horaFim.length() == 5)
            this.horaFim = LocalTime.parse(horaFim, DateTimeFormatter.ofPattern("HH:mm"));
    }

    @JsonGetter("horaFim")
    public String getHoraFimJson() {

        if (this.horaFim != null) {
            return this.horaFim.format(DateTimeFormatter.ofPattern("HH:mm"));
        } else return "";
    }

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
        return this.numeroAula != null && this.numeroAula >= 0 &&
                this.turno != null && this.horaInicio != null &&
                this.horaFim != null && this.codigoAmbiente != null &&
                !this.codigoAmbiente.isEmpty() && this.codigoDisciplina != null &&
                this.codigoDisciplina >= 0 && this.matriculaProfessor != null &&
                !this.matriculaProfessor.isEmpty();
    }

}
