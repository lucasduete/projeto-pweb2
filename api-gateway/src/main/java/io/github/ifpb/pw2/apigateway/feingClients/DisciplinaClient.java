package io.github.ifpb.pw2.apigateway.feingClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

@FeignClient(name = "cursoservice", path = "/disciplina")
public interface DisciplinaClient {

    @GetMapping
    ResponseEntity listarTodos();

    @GetMapping
    ResponseEntity buscarPorCodigo(@PathParam("{codigo}") Long codigo);

    @PostMapping
    ResponseEntity buscarPorNome(@RequestParam(value = "{nome}", required = true) String nome);
    
}
