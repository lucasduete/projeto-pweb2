package io.github.pw2.cursoservice.controllers;

import io.github.pw2.cursoservice.models.Disciplina;
import io.github.pw2.cursoservice.services.DisciplinaService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("disciplina")
public class DisciplinaController {

    private final DisciplinaService service;

    public DisciplinaController(DisciplinaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity listarTodos() {
        List<Disciplina> disciplinas = this.service.listarAll();

        if (disciplinas == null || disciplinas.isEmpty())  return ResponseEntity.noContent().build();

        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarPorCodigo(@PathParam("codigo") Long codigo) {

        if (codigo == null || codigo < 0)
            return ResponseEntity.badRequest().body("Para recuperar a disciplina e necessario passar um codigo por parametro!");

        Optional<Disciplina> disciplina = this.service.buscarPorCodigo(codigo);

        return disciplina.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity buscarPorNome(@RequestParam(name = "nome", required = true) String nome) {

        if (Strings.isBlank(nome))
            return ResponseEntity.badRequest().body("Para recuperar a disciplina e necessario passar o nome por parametro!");

        Optional<Disciplina> disciplina = this.service.buscarPorNome(nome);

        return disciplina.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

}
