package io.github.ifpb.pw2.apigateway.feingClients;

import io.github.pw2.cursoservice.models.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@FeignClient(name = "cursoservice")
public interface CursoDisciplinaClient {

    @PostMapping("/curso")
    ResponseEntity<?> salvarCursoDisciplina(@RequestBody Curso curso);

    @GetMapping("/curso")
    ResponseEntity<?> listarTodosCursos();

    @GetMapping("/curso/{codigo}")
    ResponseEntity<?> buscarCursoPorCodigo(@PathVariable(name = "codigo", required = true) Long codigo);

    @GetMapping("/curso/buscar")
    ResponseEntity<?> buscarCursoPorNome(@RequestParam(name = "nome", required = true) String nome);

    @GetMapping("/disciplina")
    ResponseEntity<?> listarTodasDisciplinas();

    @GetMapping("/disciplina/{codigo}")
    ResponseEntity<?> buscarDisciplinaPorCodigo(@PathParam("{codigo}") Long codigo);

    @PostMapping("/disciplina/buscar")
    ResponseEntity<?> buscarDisciplinaPorNome(@RequestParam(value = "nome", required = true) String nome);

}
