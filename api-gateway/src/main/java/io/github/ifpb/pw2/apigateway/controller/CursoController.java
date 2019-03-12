package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.CursoDisciplinaClient;
import io.github.pw2.cursoservice.models.Curso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("curso")
public class CursoController {

    private final CursoDisciplinaClient client;

    public CursoController(CursoDisciplinaClient client) {
        this.client = client;
    }

    @PostMapping
    public ResponseEntity salvarCursoDisciplina(@RequestBody Curso curso) {
        ResponseEntity response = client.salvarCursoDisciplina(curso);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
