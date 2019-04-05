package io.github.pw2.ambienteservice.models;

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
public class Ambiente {

    @Id
    private String codigo;

    @Column(nullable = false, unique = true)
    private String nome;

}
