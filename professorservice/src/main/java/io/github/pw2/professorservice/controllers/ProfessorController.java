package io.github.pw2.professorservice.controllers;

import io.github.pw2.professorservice.models.Professor;
import io.github.pw2.professorservice.services.ProfessorService;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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

    @GetMapping("/{matricula}")
    public ResponseEntity buscarPorMatricula(@PathVariable("matricula") String matricula) {

        if (matricula == null) {
            return ResponseEntity.badRequest().body("E necessario fornecer a Matricula para concluir esta requisicao");
        }

        Optional<Professor> professor = this.service.buscarPorMatricula(matricula);

        return professor.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity buscarPorNome(@RequestParam(name = "nome", required = true) String nome) {

        if (nome == null || nome.isEmpty()) {
            return ResponseEntity.badRequest().body("E necessario fornecer o Nome para concluir esta requisicao");
        }

        Optional<Professor> professor = this.service.buscarPorNome(nome);

        return professor.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/{matricula}")
    private ResponseEntity atualizarProfessor(@PathVariable(name = "matricula", required = true) String matricula,
                                              @RequestBody Professor professor) {

        if (matricula == null || matricula.isEmpty()) {
            return ResponseEntity.badRequest().body("Voce deve informar uma matricula valida");

        } else if (professor == null) {
            return ResponseEntity.badRequest().body("Voce deve informar os dados do professor para que sejam atualizados.");

        } else if (professor.getNome() == null || professor.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("Voce deve informar um nome valido para o professor");
        }

        try {

            Professor professorAtualizado = this.service.atualizar(professor, matricula);
            return ResponseEntity.ok(professorAtualizado);

        } catch (EntityNotFoundException enfEx) {
            enfEx.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

    }

    @DeleteMapping("/{matricula}")
    private ResponseEntity deletarProfessor(@PathVariable(name = "matricula", required = true) String matricula) {

        if (matricula == null || matricula.isEmpty())
            return ResponseEntity.badRequest().body("Voce deve informar uma matricula valida.");

        try {

            this.service.deletar(matricula);
            return ResponseEntity.ok().build();

        } catch (EntityNotFoundException enfEx) {
            enfEx.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

    }


}
