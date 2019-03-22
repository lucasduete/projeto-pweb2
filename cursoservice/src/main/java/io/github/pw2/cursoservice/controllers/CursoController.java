package io.github.pw2.cursoservice.controllers;

import io.github.pw2.cursoservice.models.Curso;
import io.github.pw2.cursoservice.models.Disciplina;
import io.github.pw2.cursoservice.services.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("curso")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity saveCursoDisciplinas(@RequestBody Curso curso) {

        // Este unico EndPoint deve ser responsavel por persistir curso e Disciplina

        // Verifica se o curso e nulo e se algum de seus atributos sao nulos

        if (curso == null) {
            return ResponseEntity.badRequest().body("Nao foi enviado nenhum curso para ser persistido.");
        } else if (curso.getCodigo() == null) {
            return ResponseEntity.badRequest().body("E necessario enviar uma matricula valida para que o curso seja persistido.");
        } else if (curso.getNome() == null || curso.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("E necessario enviar um nome valido para que o curso seja persistido.");
        } else if (curso.getDescricao() == null || curso.getDescricao().isEmpty()) {
            return ResponseEntity.badRequest().body("E necessario enviar uma descricao valida para que o curso seja persistido.");
        }


        // Verifica se as disciplinas sao nulas e se algum de seus atributos sao nulos

        if (curso.getDisciplinas() == null) {
            //return ResponseEntity.badRequest().body("E necessario enviar as disciplinas do curso para que ele seja persistido.");
        } else if (verificaDisciplinasValidas(curso.getDisciplinas())) {
            return ResponseEntity.badRequest().body("Uma ou mais Disciplinas enviadas sao invalidas, verifique os atributos e tente novamente.");
        }

        // Se nada for null entao pode-se persistir o ambiente

        Curso cursoSalvo = this.service.salvar(curso);

        if (cursoSalvo == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nao foi possivel persitir esta entidade.");
        } else {
            return ResponseEntity.ok(curso);
        }

    }

    @GetMapping
    public ResponseEntity listarTodos() {
        List<Curso> cursos = this.service.listarAll();

        if (cursos ==  null || cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cursos);
        }

    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarPorCodigo(@PathVariable(name = "codigo", required = true) Long codigo) {

        Optional<Curso> curso = this.service.buscarPorCodigo(codigo);

        return curso.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity buscarPorNome(@RequestParam(name = "nome", required = true) String nome) {

        Optional<Curso> curso = this.service.buscarPorNome(nome);

        return curso.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    private boolean verificaDisciplinasValidas(List<Disciplina> disciplinas) {

        Optional<Disciplina> anyDisciplinaInvalida = disciplinas
                .stream()
                .filter(disciplina -> (disciplina.getCodigo() == null || disciplina.getNome() == null || disciplina.getCodigo() == null))
                .findAny();

        return anyDisciplinaInvalida.isPresent();
    }
}
