package io.github.ifpb.pw2.apigateway.controller.visitante;

import io.github.pw2.cursoservice.models.Curso;
import io.github.pw2.cursoservice.services.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("visitante/curso")
public class CursoVisitanteController {

    private final CursoService cursoService;

    public CursoVisitanteController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity listarAmbientes() {

        List<Curso> cursos = this.cursoService.listarAll();

        if (cursos == null || cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cursos);
        }

    }

}
