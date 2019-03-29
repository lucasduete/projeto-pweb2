package io.github.ifpb.pw2.apigateway.valueObjects;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class HorarioVO {

    private Long id;

    private String nomeCurso;

    private Integer numeroPeriodo;

    private List<DiaLetivoVO> diasLetivos;
}
