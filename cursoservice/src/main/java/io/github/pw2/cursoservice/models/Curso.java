package io.github.pw2.cursoservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Curso implements Serializable {

    @Id
    private Long codigo;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Disciplina> disciplinas;

    public Curso clone() {

        return new Curso(this.codigo, this.nome, this.descricao, new ArrayList<>(this.getDisciplinas()));
    }

}
