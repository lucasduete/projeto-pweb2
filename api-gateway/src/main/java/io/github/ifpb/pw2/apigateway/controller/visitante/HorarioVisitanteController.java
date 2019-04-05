package io.github.ifpb.pw2.apigateway.controller.visitante;

import io.github.ifpb.pw2.apigateway.service.HorarioServiceCQRS;
import io.github.ifpb.pw2.apigateway.valueObjects.HorarioVO;
import io.github.pw2.horarioservice.exceptions.CursoSemHorarioAcademicoException;
import io.github.pw2.horarioservice.models.Aula;
import io.github.pw2.horarioservice.services.AulaService;
import io.github.pw2.horarioservice.services.HorarioVOService;
import io.github.pw2.horarioservice.valueObjects.HorarioAcademicoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("visitante/horario")
public class HorarioVisitanteController {

    private final AulaService aulaService;
    private final HorarioVOService horarioVOService;
    private final HorarioServiceCQRS horarioServiceCQRS;

    public HorarioVisitanteController(AulaService aulaService, HorarioVOService horarioVOService, HorarioServiceCQRS horarioServiceCQRS) {
        this.aulaService = aulaService;
        this.horarioVOService = horarioVOService;
        this.horarioServiceCQRS = horarioServiceCQRS;
    }

    @GetMapping("/curso/{codigoCurso}")
    public ResponseEntity buscarPorCurso(@PathVariable(name = "codigoCurso", required = true) Long codigoCurso) {

        if (codigoCurso == null || codigoCurso <= 0)
            return ResponseEntity.badRequest().build();

        try {
            List<HorarioVO> horariosPorCurso = this.horarioServiceCQRS.buscarPorCurso(codigoCurso);

            if (horariosPorCurso.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(horariosPorCurso);
            }

        } catch (CursoSemHorarioAcademicoException cshaEx) {
            cshaEx.printStackTrace();
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/professor/{matriculaProfessor}")
    public ResponseEntity buscarPorProfessor(@PathVariable(name = "matriculaProfessor", required = true) String matriculaProfessor) {

        if (matriculaProfessor == null || matriculaProfessor.isEmpty())
            return ResponseEntity.badRequest().body("Voce deve informar repositories Matricula do Professor para realizar repositories listagem");

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
            return ResponseEntity.badRequest().body("Voce deve informar um Codigo de Ambiente valido para realizar repositories listagem");

        List<Aula> aulasNoAmbiente = this.aulaService.findAllByCodigoAmbiente(codigoAmbiente);

        if (aulasNoAmbiente == null || aulasNoAmbiente.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        HorarioAcademicoVO horarioAcademicoVO = this.horarioVOService.processToVO(aulasNoAmbiente);

        return ResponseEntity.ok(horarioAcademicoVO);
    }

}
