package io.github.ifpb.pw2.apigateway.model;

import org.springframework.security.core.GrantedAuthority;

public enum Papel implements GrantedAuthority {
    COORDENADOR,
    PROFESSOR,
    ALUNO,
    ADMINISTRADOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
