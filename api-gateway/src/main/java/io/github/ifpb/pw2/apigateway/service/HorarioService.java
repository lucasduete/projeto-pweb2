package io.github.ifpb.pw2.apigateway.service;

import io.github.ifpb.pw2.apigateway.feingClients.AmbienteClient;
import io.github.ifpb.pw2.apigateway.feingClients.CursoDisciplinaClient;
import io.github.ifpb.pw2.apigateway.feingClients.HorarioClient;
import io.github.ifpb.pw2.apigateway.feingClients.ProfessorClient;
import io.github.ifpb.pw2.apigateway.valueObjects.AulaVO;
import io.github.ifpb.pw2.apigateway.valueObjects.DiaLetivoVO;
import io.github.ifpb.pw2.apigateway.valueObjects.HorarioVO;
import io.github.pw2.cursoservice.models.Curso;
import io.github.pw2.horarioservice.models.HorarioAcademico;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioService {

    private final HorarioClient horarioClient;
    private final CursoDisciplinaClient cursoDisciplinaClient;
    private final AmbienteClient ambienteClient;
    private final ProfessorClient professorClient;

    public HorarioService(HorarioClient client, CursoDisciplinaClient cursoDisciplinaClient,
                          AmbienteClient ambienteClient, ProfessorClient professorClient) {
        this.horarioClient = client;
        this.cursoDisciplinaClient = cursoDisciplinaClient;
        this.ambienteClient = ambienteClient;
        this.professorClient = professorClient;
    }

    public List<HorarioVO> buscarPorCurso(Long codCurso) {

        List<DiaLetivoVO> diaLetivoVOS = new ArrayList<>();
        ResponseEntity<List<HorarioAcademico>> responseEntity = horarioClient.buscarPorCurso(codCurso);

        List<HorarioAcademico> horarios = responseEntity.getBody();
        List<HorarioVO>  horarioVOS = new ArrayList<>();

        horarios.forEach(h -> {
            HorarioVO horarioVO = new HorarioVO();
            Curso curso = cursoDisciplinaClient.buscarCursoPorCodigo(codCurso).getBody();
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
                    aulaVO.setNomeAmbiente(ambienteClient.buscarPorCodigo(a.getCodigoAmbiente()).getBody().getNome());
                    aulaVO.setNomeProfessor(professorClient.buscarPorMatricula(a.getMatriculaProfessor()).getBody().getNome());
                    aulaVO.setNomeDisciplina(cursoDisciplinaClient.buscarPorCodigo(a.getCodigoDisciplina()).getBody().getNome());
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
