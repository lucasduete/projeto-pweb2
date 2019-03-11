package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.ProfessorClient;
import io.github.pw2.professorservice.models.Professor;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity salvarProfessor(@RequestBody Professor professor) {
        return this.professorClient.salvarProfessor(professor);
    }

}
