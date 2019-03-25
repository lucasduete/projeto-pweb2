package io.github.pw2.horarioservice.services;

import io.github.pw2.horarioservice.models.Aula;
import io.github.pw2.horarioservice.models.DiaLetivo;
import io.github.pw2.horarioservice.valueObjects.AulaVO;
import io.github.pw2.horarioservice.valueObjects.DiaLetivoVO;
import io.github.pw2.horarioservice.valueObjects.HorarioAcademicoVO;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HorarioVOService {

    public HorarioAcademicoVO processToVO(@NotNull final List<Aula> aulasToProcess) {

        HorarioAcademicoVO horarioAcademico = new HorarioAcademicoVO();

        Map<DayOfWeek, DiaLetivoVO> diasLetivosVO = new HashMap<>();

        aulasToProcess.forEach(aula -> {

            DayOfWeek dia = aula.getDiaLetivo().getDia();

            if (!diasLetivosVO.containsKey(dia)) {
                diasLetivosVO.put(dia,
                        this.convertDiaLetivoToVO(aula.getDiaLetivo()));
            }

            AulaVO aulaVO = AulaVO.builder()
                    .numeroAula(aula.getNumeroAula())
                    .turno(aula.getTurno())
                    .horaInicio(aula.getHoraInicio())
                    .horaFim(aula.getHoraFim())
                    .codigoAmbiente(aula.getCodigoAmbiente())
                    .matriculaProfessor(aula.getMatriculaProfessor())
                    .codigoCurso(aula.getDiaLetivo().getHorarioAcademico().getCodigoCurso())
                    .numeroPeriodo(aula.getDiaLetivo().getHorarioAcademico().getNumeroPeriodo())
                    .codigoDisciplina(aula.getCodigoDisciplina())
                    .build();

            diasLetivosVO.get(dia).addAula(aulaVO);
        });


        horarioAcademico.setDiasLetivos(
                new ArrayList<>(diasLetivosVO.values())
        );

        return horarioAcademico;
    }

    private DiaLetivoVO convertDiaLetivoToVO(@NotNull final DiaLetivo diaLetivo) {

        return DiaLetivoVO.builder()
                .dia(diaLetivo.getDia())
                .aulas(new ArrayList<>())
                .build();
    }

}
