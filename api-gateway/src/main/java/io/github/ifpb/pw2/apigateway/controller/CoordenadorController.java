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

    @PostMapping("/login")
    public ResponseEntity<Coordenador> login(@RequestBody Coordenador coordenador){
        Coordenador buscado = client.recuperar(coordenador.getMatricula()).getBody();
        if(buscado!= null && buscado.getSenha().equals(coordenador.getSenha())){
            return ResponseEntity.ok(buscado);
        }
        return ResponseEntity.notFound().build();
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
    public ResponseEntity<Coordenador> recuperar(@PathVariable("matricula") Long matricula){
        return this.client.recuperar(matricula);
    }
}
