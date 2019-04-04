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

    private String entityName;
    private Object payload;
    private Operation operation;

    public enum Operation {

        PERSIST, UPDATE, DELETE
    }

}