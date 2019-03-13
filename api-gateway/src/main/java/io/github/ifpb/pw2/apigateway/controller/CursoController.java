package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.CursoDisciplinaClient;
import io.github.pw2.cursoservice.models.Curso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity listarTodosCursos() {
        ResponseEntity response = client.listarTodosCursos();

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarCursoPorCodigo(@PathVariable(name = "codigo", required = true) Long codigo) {
        ResponseEntity response = client.buscarCursoPorCodigo(codigo);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/buscar")
    public ResponseEntity buscarCursoPorNome(@RequestParam(name = "nome", required = true) String nome) {
        ResponseEntity response = client.buscarCursoPorNome(nome);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
