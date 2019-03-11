package io.github.ifpb.pw2.apigateway.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import io.github.ifpb.pw2.apigateway.feingClients.CoordenadorClient;
import io.github.pw2.coordenadorservice.models.Coordenador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coordenador")
public class CoordenadorController {

    private CoordenadorClient client;

    public CoordenadorController(CoordenadorClient client) {
        this.client = client;
    }

    @PostMapping
    public ResponseEntity<Coordenador> login(@RequestBody Coordenador coordenador){
        Coordenador buscado = client.recuperar(coordenador.getMatricula()).getBody();
        if(buscado!= null && buscado.getSenha().equals(coordenador.getSenha())){
            return ResponseEntity.ok(buscado);
        }
        return ResponseEntity.notFound().build();
    }
}
