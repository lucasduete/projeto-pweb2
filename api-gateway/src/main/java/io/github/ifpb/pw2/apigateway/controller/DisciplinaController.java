package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.CursoDisciplinaClient;
import io.github.pw2.cursoservice.models.Curso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("disciplina")
public class DisciplinaController {

    private final CursoDisciplinaClient client;

    public DisciplinaController(CursoDisciplinaClient client) {
        this.client = client;
    }

    @GetMapping
    public ResponseEntity listarTodasDisciplinas() {
        ResponseEntity response = client.listarTodasDisciplinas();

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarDisciplinaPorCodigo(@PathParam("{codigo}") Long codigo) {
        ResponseEntity response = client.buscarDisciplinaPorCodigo(codigo);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/buscar")
    public ResponseEntity buscarDisciplinaPorNome(@RequestParam(value = "nome", required = true) String nome) {
        ResponseEntity response = client.buscarDisciplinaPorNome(nome);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
