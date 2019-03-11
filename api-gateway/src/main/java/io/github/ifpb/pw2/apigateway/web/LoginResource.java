package io.github.ifpb.pw2.apigateway.web;

import io.github.ifpb.pw2.apigateway.controller.CoordenadorController;
import io.github.pw2.coordenadorservice.models.Coordenador;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginResource {

    private CoordenadorController service;

    public LoginResource(CoordenadorController service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Coordenador> login(@RequestBody Coordenador coordenador) {
        Coordenador buscado = service.login(coordenador);
        return buscado == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(buscado);
    }
}
