package io.github.ifpb.pw2.apigateway.service;

import io.github.ifpb.pw2.apigateway.valueObjects.AulaVO;
import io.github.ifpb.pw2.apigateway.valueObjects.DiaLetivoVO;
import io.github.ifpb.pw2.apigateway.valueObjects.HorarioVO;
import io.github.pw2.ambienteservice.services.AmbienteService;
import io.github.pw2.cursoservice.models.Curso;
import io.github.pw2.cursoservice.services.CursoService;
import io.github.pw2.horarioservice.models.HorarioAcademico;
import io.github.pw2.horarioservice.services.HorarioAcademicoService;
import io.github.pw2.professorservice.services.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioServiceCQRS {

    private final HorarioAcademicoService horarioservice;
    private final CursoService cursoService;
    private final AmbienteService ambienteService;
    private final ProfessorService professorService;

    public HorarioServiceCQRS(HorarioAcademicoService horarioservice, CursoService cursoService, AmbienteService ambienteService, ProfessorService professorService) {        this.horarioservice = horarioservice;
        this.cursoService = cursoService;
        this.ambienteService = ambienteService;
        this.professorService = professorService;
    }

    public List<HorarioVO> buscarPorCurso(Long codCurso) throws Exception {

        List<HorarioAcademico> horarios = horarioservice.listarPorCurso(codCurso);

        List<DiaLetivoVO> diaLetivoVOS = new ArrayList<>();
        List<HorarioVO>  horarioVOS = new ArrayList<>();

        horarios.forEach(h -> {
            HorarioVO horarioVO = new HorarioVO();
            Curso curso = cursoService.buscarPorCodigo(codCurso).get();
            horarioVO.setId(h.getId());
            horarioVO.setNumeroPeriodo(h.getNumeroPeriodo());
            horarioVO.setNomeCurso(curso.getNome());
            h.getDiasLetivos().forEach(d -> {
                DiaLetivoVO diaLetivoVO = new DiaLetivoVO();
                diaLetivoVO.setDia(d.getDia());
                diaLetivoVO.setId(d.getId());

                List<AulaVO> aulaVOS = new ArrayList<>();

                d.getAulas().forEach(a -> {
                    AulaVO aulaVO = new AulaVO();
                    aulaVO.setHoraFim(a.getHoraFim());
                    aulaVO.setHoraInicio(a.getHoraInicio());
                    aulaVO.setNumeroAula(a.getNumeroAula());
                    aulaVO.setTurno(AulaVO.TipoTurno.valueOf(a.getTurno().name()));
                    aulaVO.setNomeAmbiente(ambienteService.recuperarPorCodigo(a.getCodigoAmbiente()).get().getNome());
                    aulaVO.setNomeProfessor(professorService.buscarPorMatricula(a.getMatriculaProfessor()).get().getNome());
                    aulaVO.setNomeDisciplina(cursoService.buscarPorCodigo(a.getCodigoDisciplina()).get().getNome());
                    aulaVOS.add(aulaVO);
                });

                diaLetivoVO.setAulas(aulaVOS);
                diaLetivoVOS.add(diaLetivoVO);
            });
            horarioVO.setDiasLetivos(diaLetivoVOS);
            horarioVOS.add(horarioVO);
        });
        return horarioVOS;
    }

}
