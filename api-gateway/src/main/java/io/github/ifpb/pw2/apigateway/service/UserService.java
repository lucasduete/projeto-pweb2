package io.github.ifpb.pw2.apigateway.service;

import io.github.ifpb.pw2.apigateway.feingClients.CoordenadorClient;
import io.github.ifpb.pw2.apigateway.model.Papel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component("userDetailsService")
public class UserService implements UserDetailsService {

    private final CoordenadorClient coordenadorClient;

    public UserService(CoordenadorClient coordenadorClient) {
        this.coordenadorClient = coordenadorClient;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Set<Papel> papeis = new HashSet<>();
        papeis.add(Papel.COORDENADOR);
        return Optional.of(coordenadorClient.recuperar(login).getBody())
                .map( coordenador -> new User(coordenador.getMatricula(), coordenador.getSenha(), papeis)).get();
    }
}
