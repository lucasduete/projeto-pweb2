package io.github.ifpb.pw2.apigateway.feingClients;

import io.github.pw2.coordenadorservice.models.Coordenador;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "coordenadorservice", path = "/coordenador")
public interface CoordenadorClient {

    @GetMapping(value = "/{matricula}")
    public ResponseEntity<Coordenador> recuperar(@PathVariable("matricula") Long matricula);

    @PostMapping
    public ResponseEntity<Coordenador> saveCoordenador(@RequestBody Coordenador coordenador);

    @GetMapping
    public ResponseEntity<List<Coordenador>> recuperarTodos();
}
