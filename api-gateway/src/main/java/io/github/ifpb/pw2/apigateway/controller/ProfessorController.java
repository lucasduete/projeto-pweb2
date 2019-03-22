package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.ProfessorClient;
import io.github.pw2.professorservice.models.Professor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("professor")
public class ProfessorController {

    private final ProfessorClient professorClient;

    public ProfessorController(ProfessorClient professorClient) {
        this.professorClient = professorClient;
    }

    @PostMapping
    public ResponseEntity salvarProfessor(@RequestBody Professor professor) {
        ResponseEntity response = this.professorClient.salvarProfessor(professor);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping
    public ResponseEntity listarTodos() {
        ResponseEntity response = this.professorClient.listarTodos();

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/{matricula}")
    public ResponseEntity buscarPorId(@PathVariable("matricula") Long matricula) {
        ResponseEntity response = this.professorClient.buscarPorMatricula(matricula);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/buscar")
    public ResponseEntity buscarPorNome(@RequestParam(name = "nome", required = true) String nome) {
        ResponseEntity response = this.professorClient.buscarPorNome(nome);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
