package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.CursoClient;
import io.github.pw2.cursoservice.models.Curso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("curso")
public class CursoController {

    private final CursoClient cursoClient;

    public CursoController(CursoClient cursoClient) {
        this.cursoClient = cursoClient;
    }

    @PostMapping
    public ResponseEntity salvarCursoDisciplina(@RequestBody Curso curso) {
        ResponseEntity response = cursoClient.salvarCursoDisciplina(curso);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
