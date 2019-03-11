package io.github.pw2.professorservice.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Professor {

    @Id
    private Long matricula;

    @Column(nullable = false, unique = true)
    private String nome;

}
