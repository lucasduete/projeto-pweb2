package io.github.ifpb.pw2.apigateway.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import io.github.ifpb.pw2.apigateway.feingClients.CoordenadorClient;
import io.github.pw2.coordenadorservice.models.Coordenador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("coordenador")
public class CoordenadorController {

    private CoordenadorClient client;

    public CoordenadorController(CoordenadorClient client) {
        this.client = client;
    }

    @PostMapping
    public ResponseEntity<Coordenador> salvar(@RequestBody Coordenador coordenador){
        return this.client.saveCoordenador(coordenador);
    }

    @GetMapping
    public ResponseEntity<List<Coordenador>> listarTodos(){
        return this.client.recuperarTodos();
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Coordenador> recuperar(@PathVariable("matricula") String matricula){
        return this.client.recuperar(matricula);
    }
}
