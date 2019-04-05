package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.service.jwt.TokenProvider;
import io.github.pw2.coordenadorservice.models.Coordenador;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public LoginController(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody final Coordenador coordenador) {

        if (coordenador == null) {
            return ResponseEntity.badRequest().body("Para efeturar o login voce deve enviar as credencias do Coordenador");
        } else if (coordenador.getMatricula() == null || coordenador.getMatricula().isEmpty()) {
            return ResponseEntity.badRequest().body("Para efeturar o login voce deve enviar repositories matricula do Coordenador");
        } else if (coordenador.getSenha() == null || coordenador.getSenha().isEmpty()) {
            return ResponseEntity.badRequest().body("Para efeturar o login voce deve enviar repositories senha do Coordenador");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(coordenador.getMatricula(), coordenador.getSenha());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);

        coordenador.setSenha(jwt);

        return new ResponseEntity<>(coordenador, headers, HttpStatus.OK);
    }

}
