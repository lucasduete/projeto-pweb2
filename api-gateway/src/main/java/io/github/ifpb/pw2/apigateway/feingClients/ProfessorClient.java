package io.github.ifpb.pw2.apigateway.feingClients;

import io.github.pw2.professorservice.models.Professor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "professorservice", path = "/professor")
public interface ProfessorClient {

    @PostMapping
    ResponseEntity salvarProfessor(@RequestBody Professor professor);

    @GetMapping("buscar/")
    ResponseEntity buscarPorNome(@RequestParam(name = "nome", required = true) String nome);

    @GetMapping("{matricula}")
    ResponseEntity buscarPorId(@PathVariable("matricula") Long matricula);

    @GetMapping
    ResponseEntity listarTodos();

    @PostMapping
    ResponseEntity saveProfessor(@RequestBody Professor professor);

}
