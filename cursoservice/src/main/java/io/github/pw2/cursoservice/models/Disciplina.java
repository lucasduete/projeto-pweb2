    package io.github.pw2.cursoservice.models;

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

}
