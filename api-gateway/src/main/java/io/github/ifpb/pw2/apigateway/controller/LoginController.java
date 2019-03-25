package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.CoordenadorClient;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final CoordenadorClient coordenadorClient;
    private final AuthenticationManager authenticationManager;

    public LoginController(TokenProvider tokenProvider, PasswordEncoder passwordEncoder, CoordenadorClient coordenadorClient, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.coordenadorClient = coordenadorClient;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Object> login(@RequestParam(name = "matricula", required = true) final String matricula,
                                        @RequestParam(name = "senha", required = true) final String senha) {

        if (matricula == null || matricula.isEmpty()) {
            return ResponseEntity.badRequest().body("Para efeturar o login voce deve enviar a matricula do Coordenador");
        } else if (senha == null || senha.isEmpty()) {
            return ResponseEntity.badRequest().body("Para efeturar o login voce deve enviar a senha do Coordenador");
        }

        Coordenador coordenador = coordenadorClient.recuperar(matricula).getBody();

        if (coordenador == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else if (!passwordEncoder.matches(senha, coordenador.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(coordenador.getMatricula(), coordenador.getSenha());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);

        coordenador.setSenha(jwt);

        return new ResponseEntity<>(coordenador, headers, HttpStatus.OK);
    }

}
