package io.github.pw2;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class EventMessage implements Serializable {

    private ServiceType serviceName;
    private Object payload;
    private Operation operation;

    public enum ServiceType {

        HORARIOSERVICE, PROFESSORSERVICE, AMBIENTESERVICE
    }

    public enum Operation {

        PERSIST, UPDATE, DELETE
    }

}