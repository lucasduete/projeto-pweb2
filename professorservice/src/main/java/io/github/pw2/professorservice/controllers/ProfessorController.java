package io.github.pw2.professorservice.controllers;

import io.github.pw2.professorservice.models.Professor;
import io.github.pw2.professorservice.services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
