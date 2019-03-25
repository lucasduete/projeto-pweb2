package io.github.pw2.horarioservice.controllers;

import io.github.pw2.horarioservice.exceptions.CursoSemHorarioAcademicoException;
import io.github.pw2.horarioservice.models.HorarioAcademico;
import io.github.pw2.horarioservice.services.HorarioAcademicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("horarioacademico")
public class HorarioAcademicoController {

    private final HorarioAcademicoService service;

    public HorarioAcademicoController(HorarioAcademicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity salvarHorario(@RequestBody HorarioAcademico horarioAcademico) {

        if (horarioAcademico == null || !horarioAcademico.validate())
            return ResponseEntity.badRequest().build();

        HorarioAcademico horarioSalvo = this.service.salvarHorario(horarioAcademico);

        if (horarioSalvo != null) {
            return ResponseEntity.ok(horarioSalvo);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/curso/{codigoCurso}")
    public ResponseEntity buscarPorCurso(@PathVariable(name = "codigoCurso", required = true) Long codigoCurso) {

        if (codigoCurso == null || codigoCurso <= 0)
            return ResponseEntity.badRequest().build();

        try {
            List<HorarioAcademico> horariosPorCurso = this.service.listarPorCurso(codigoCurso);

            if (horariosPorCurso.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(horariosPorCurso);
            }

        } catch (CursoSemHorarioAcademicoException cshaEx) {
            cshaEx.printStackTrace();
            return ResponseEntity.noContent().build();
        }


    }

}
