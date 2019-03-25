package io.github.pw2.horarioservice.valueObjects;

import io.github.pw2.horarioservice.enums.TipoTurno;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class AulaVO implements Serializable {

    private Integer numeroAula;
    private TipoTurno turno;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    private Long codigoAmbiente;
    private String matriculaProfessor;

    private Long codigoCurso;
    private Integer numeroPeriodo;
    private Long codigoDisciplina;

}
