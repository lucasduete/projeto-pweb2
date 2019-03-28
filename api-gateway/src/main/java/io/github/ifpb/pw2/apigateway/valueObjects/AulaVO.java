package io.github.ifpb.pw2.apigateway.valueObjects;

import com.google.common.collect.ImmutableList;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AulaVO {

    private Integer numeroAula;
    private TipoTurno turno;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String nomeAmbiente;
    private String nomeProfessor;
    private Integer numeroPeriodo;
    private String nomeDisciplina;
    public enum TipoTurno {

        MANHA("Manha"),
        TARDE("Tarde"),
        NOITE("Noite");

        private String nome;

        TipoTurno(String nome) {
            this.nome = nome;
        }

        public static List<String> valuesToString() {
            List<String> values = new ArrayList<>();

            for (TipoTurno value : TipoTurno.values()) values.add(value.getNome());

            return ImmutableList.copyOf(values);
        }

        public static TipoTurno of(String nome) {
            for (TipoTurno value : TipoTurno.values())
                if (value.getNome().toUpperCase().equals(nome.toUpperCase())) return value;

            return TipoTurno.MANHA;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public String toString() {
            return this.nome;
        }

    }
}
