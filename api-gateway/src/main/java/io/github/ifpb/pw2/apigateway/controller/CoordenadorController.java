package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.CoordenadorClient;
import io.github.pw2.coordenadorservice.models.Coordenador;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("coordenador")
public class CoordenadorController {

    private final CoordenadorClient client;
    private final PasswordEncoder passwordEncoder;

    public CoordenadorController(CoordenadorClient client, PasswordEncoder passwordEncoder) {
        this.client = client;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam(name = "matricula", required = true) final String matricula,
                                        @RequestParam(name = "senha", required = true) final String senha) {

        if (matricula == null || matricula.isEmpty()) {
            return ResponseEntity.badRequest().body("Para efeturar o login voce deve enviar a matricula do Coordenador");
        } else if (senha == null || senha.isEmpty()) {
            return ResponseEntity.badRequest().body("Para efeturar o login voce deve enviar a senha do Coordenador");
        }

        Coordenador coordenadorDB = client.recuperar(matricula).getBody();

        if (coordenadorDB == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else if (passwordEncoder.matches(senha, coordenadorDB.getSenha())) {
            return ResponseEntity.ok(coordenadorDB);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
