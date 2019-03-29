    package io.github.pw2.cursoservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Disciplina {

    @Id
    private Long codigo;

    @Column(nullable = false)
    private String nome;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_codigo", nullable = false)
    private Curso curso;
}
