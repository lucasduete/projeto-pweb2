package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.ProfessorClient;
import io.github.pw2.professorservice.models.Professor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("professor")
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

}
