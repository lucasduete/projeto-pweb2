package io.github.ifpb.pw2.apigateway.feingClients;

import io.github.pw2.cursoservice.models.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cursoservice", path = "/curso")
public interface CursoClient {

    @PostMapping
    ResponseEntity salvarCursoDisciplina(@RequestBody Curso curso);

    @GetMapping
    ResponseEntity listarTodos();

    @GetMapping
    ResponseEntity buscarPorCodigo(@PathVariable(name = "codigo", required = true) Long codigo);

    @GetMapping("/buscar")
    ResponseEntity buscarPorNome(@RequestParam(name = "codigo", required = true) String nome);

}
