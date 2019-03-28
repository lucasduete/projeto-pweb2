package io.github.pw2.cursoservice.controllers;

import io.github.pw2.cursoservice.models.Disciplina;
import io.github.pw2.cursoservice.services.DisciplinaService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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

        if (disciplinas == null || disciplinas.isEmpty()) return ResponseEntity.noContent().build();

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

    @PutMapping("/{codigoDisciplina}")
    private ResponseEntity atualizarDisciplina(@PathVariable(name = "codigoDisciplina", required = true) Long codigoDisciplina,
                                               @RequestBody Disciplina disciplina) {

        if (codigoDisciplina == null || codigoDisciplina <= 0)
            return ResponseEntity.badRequest().body("Voce deve informar um codigo de disciplina valido");

        if (disciplina == null || disciplina.getNome() == null || disciplina.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("Voce deve informar o nome da disciplina para que seja atualizada");

        }

        try {

            Disciplina disciplinaAtualizada = this.service.atualizar(disciplina, codigoDisciplina);
            return ResponseEntity.ok(disciplinaAtualizada);

        } catch (EntityNotFoundException enfEx) {
            enfEx.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

    }

    @DeleteMapping("/{codigoDisciplina}")
    private ResponseEntity deletarDisciplina(@PathVariable(name = "codigoDisciplina", required = true) Long codigoDisciplina) {

        if (codigoDisciplina == null || codigoDisciplina <= 0)
            return ResponseEntity.badRequest().body("Voce deve informar um codigo de Disciplina valido");

        try {

            this.service.deletar(codigoDisciplina);
            return ResponseEntity.ok().build();

        } catch (EntityNotFoundException enfEx) {
            enfEx.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

    }

}
