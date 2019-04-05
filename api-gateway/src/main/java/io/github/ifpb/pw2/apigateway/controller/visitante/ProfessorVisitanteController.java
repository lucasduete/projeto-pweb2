package io.github.ifpb.pw2.apigateway.controller.visitante;

import io.github.pw2.professorservice.models.Professor;
import io.github.pw2.professorservice.services.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("visitante/professor")
public class ProfessorVisitanteController {

    private final ProfessorService professorService;

    public ProfessorVisitanteController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity listarProfessores() {

        List<Professor> professores = this.professorService.listarAll();

        if (professores == null || professores.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(professores);
        }

    }

}
