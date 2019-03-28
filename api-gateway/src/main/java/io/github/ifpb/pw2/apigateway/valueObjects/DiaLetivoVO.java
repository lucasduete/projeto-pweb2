package io.github.ifpb.pw2.apigateway.valueObjects;

import lombok.*;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DiaLetivoVO {

    private Long id;

    private DayOfWeek dia;

    private List<AulaVO> aulas;
}
