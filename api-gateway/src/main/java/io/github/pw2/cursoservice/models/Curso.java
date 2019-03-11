package io.github.pw2.cursoservice.models;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Curso {

    private Long codigo;
    private String nome;
    private String descricao;
    private List<Disciplina> disciplinas;

}
