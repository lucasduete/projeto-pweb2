package io.github.pw2.professorservice.controllers;

import io.github.pw2.professorservice.models.Professor;
import io.github.pw2.professorservice.services.ProfessorService;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("professor")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity saveProfessor(@RequestBody Professor professor) {

        // Verifica se o professor e nulo e se algum de seus atributos sao nulos

        if (professor == null) {
            return ResponseEntity.badRequest().body("Nao foi enviado nenhum professor para ser persistido.");
        } else if (professor.getMatricula() == null) {
            return ResponseEntity.badRequest().body("E necessario enviar uma matricula valida para que o professor seja persistido.");
        } else if (professor.getNome() == null || professor.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("E necessario enviar um nome valido para que o professor seja persistido.");
        }

        // Se nada for null entao pode-se persistir o ambiente

        Professor professorSalvo = this.service.salvar(professor);

        if (professorSalvo == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nao foi possivel persitir esta entidade.");
        } else {
            return ResponseEntity.ok(professor);
        }

    }

    @GetMapping
    public ResponseEntity listarTodos() {
        List<Professor> professores = this.service.listarAll();

        if (professores == null || professores.size() == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(professores);
        }
    }

    @GetMapping("{matricula}")
    public ResponseEntity buscarPorId(@PathVariable("matricula") Long matricula) {

        if (matricula == null) {
            return ResponseEntity.badRequest().body("E necessario fornecer a Matricula para concluir esta requisicao");
        }

        Optional<Professor> professor = this.service.buscarPorMatricula(matricula);

        if (!professor.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(professor);
        }

    }

    @GetMapping("buscar/")
    public ResponseEntity buscarPorNome(@RequestParam(name = "nome", required = true) String nome) {

        if (nome.isEmpty()) {
            return ResponseEntity.badRequest().body("E necessario fornecer o Nome para concluir esta requisicao");
        }

        Optional<Professor> professor = this.service.buscarPorNome(nome);

        if (!professor.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(professor);
        }

    }

}
