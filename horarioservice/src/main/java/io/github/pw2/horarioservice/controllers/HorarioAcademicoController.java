package io.github.pw2.horarioservice.controllers;

import io.github.pw2.horarioservice.exceptions.CursoSemHorarioAcademicoException;
import io.github.pw2.horarioservice.models.Aula;
import io.github.pw2.horarioservice.models.HorarioAcademico;
import io.github.pw2.horarioservice.services.AulaService;
import io.github.pw2.horarioservice.services.HorarioAcademicoService;
import io.github.pw2.horarioservice.services.HorarioVOService;
import io.github.pw2.horarioservice.valueObjects.HorarioAcademicoVO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "horario")
public class HorarioAcademicoController {

    private final AulaService aulaService;
    private final HorarioVOService horarioVOService;
    private final HorarioAcademicoService horarioService;

    public HorarioAcademicoController(AulaService aulaService, HorarioVOService horarioVOService, HorarioAcademicoService horarioService) {
        this.aulaService = aulaService;
        this.horarioVOService = horarioVOService;
        this.horarioService = horarioService;
    }

    @PostMapping
    public ResponseEntity salvarHorario(@RequestBody HorarioAcademico horarioAcademico) {

        if (horarioAcademico == null || !horarioAcademico.validate())
            return ResponseEntity.badRequest().build();

       HorarioAcademico horarioSalvo = this.horarioService.salvarHorario(horarioAcademico);

        if (horarioSalvo != null) {
            return ResponseEntity.ok(horarioSalvo);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/curso/{codigoCurso}")
    public ResponseEntity<List<HorarioAcademico>> buscarPorCurso(@PathVariable(name = "codigoCurso", required = true) Long codigoCurso) {

        if (codigoCurso == null || codigoCurso <= 0)
            return ResponseEntity.badRequest().build();

        try {
            List<HorarioAcademico> horariosPorCurso = this.horarioService.listarPorCurso(codigoCurso);

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

    @GetMapping("/professor/{matriculaProfessor}")
    public ResponseEntity buscarPorProfessor(@PathVariable(name = "matriculaProfessor", required = true) String matriculaProfessor) {

        if (matriculaProfessor == null || matriculaProfessor.isEmpty())
            return ResponseEntity.badRequest().body("Voce deve informar a Matricula do Professor para realizar a listagem");

        List<Aula> aulasDoProfessor = this.aulaService.findAllByMatriculaProfessor(matriculaProfessor);

        if (aulasDoProfessor == null || aulasDoProfessor.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        HorarioAcademicoVO horarioAcademicoVO = this.horarioVOService.processToVO(aulasDoProfessor);

        return ResponseEntity.ok(horarioAcademicoVO);
    }

    @GetMapping("/ambiente/{codigoAmbiente}")
    public ResponseEntity buscarPorAmbiente(@PathVariable(name = "codigoAmbiente", required = true) String codigoAmbiente) {

        if (codigoAmbiente == null || codigoAmbiente.isEmpty())
            return ResponseEntity.badRequest().body("Voce deve informar um Codigo de Ambiente valido para realizar a listagem");

        List<Aula> aulasNoAmbiente = this.aulaService.findAllByCodigoAmbiente(codigoAmbiente);

        if (aulasNoAmbiente == null || aulasNoAmbiente.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        HorarioAcademicoVO horarioAcademicoVO = this.horarioVOService.processToVO(aulasNoAmbiente);

        return ResponseEntity.ok(horarioAcademicoVO);
    }

}
