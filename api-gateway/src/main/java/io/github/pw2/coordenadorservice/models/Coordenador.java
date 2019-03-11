package io.github.pw2.coordenadorservice.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Coordenador {

    private String nome;
    private String senha;
    private Long matricula;

}
