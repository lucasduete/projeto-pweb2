package io.github.ifpb.pw2.apigateway.service;

import com.google.gson.Gson;
import io.github.ifpb.pw2.apigateway.feingClients.CursoDisciplinaClient;
import io.github.ifpb.pw2.apigateway.feingClients.HorarioClient;
import io.github.ifpb.pw2.apigateway.valueObjects.AulaVO;
import io.github.ifpb.pw2.apigateway.valueObjects.DiaLetivoVO;
import io.github.ifpb.pw2.apigateway.valueObjects.HorarioVO;
import io.github.pw2.cursoservice.models.Curso;
import io.github.pw2.horarioservice.models.HorarioAcademico;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class HorarioService {

    private final HorarioClient client;
    private final CursoDisciplinaClient cursoDisciplinaClient;

    public HorarioService(HorarioClient client, CursoDisciplinaClient cursoDisciplinaClient) {
        this.client = client;
        this.cursoDisciplinaClient = cursoDisciplinaClient;
    }

    public List<HorarioVO> buscarPorCurso(Long codCurso) {

        List<DiaLetivoVO> diaLetivoVOS = new ArrayList<>();
        ResponseEntity<List<HorarioAcademico>> responseEntity = client.buscarPorCurso(codCurso);

        List<HorarioAcademico> horarios = responseEntity.getBody();
        List<HorarioVO> hvos= new ArrayList<>();

        horarios.forEach(h->{
            HorarioVO horarioVO = new HorarioVO();
            Curso curso = cursoDisciplinaClient.buscarCursoPorCodigo(codCurso).getBody();
            horarioVO.setId(h.getId());
            horarioVO.setNumeroPeriodo(h.getNumeroPeriodo());
            horarioVO.setNomeCurso(curso.getNome());

            h.getDiasLetivos().forEach(d -> {
                DiaLetivoVO vo = new DiaLetivoVO();
                vo.setDia(d.getDia());
                vo.setId(d.getId());
                List<AulaVO> aulaVOS = new ArrayList<>();
                d.getAulas().forEach(a -> {
                    AulaVO aulaVO = new AulaVO();
                    aulaVO.setHoraFim(a.getHoraFim());
                    aulaVO.setHoraInicio(a.getHoraInicio());
                    aulaVO.setNumeroAula(a.getNumeroAula());
                    aulaVO.setTurno(AulaVO.TipoTurno.valueOf(a.getTurno().name()));
                });
            });
            hvos.add(horarioVO);
        });
        return hvos;
    }
}
