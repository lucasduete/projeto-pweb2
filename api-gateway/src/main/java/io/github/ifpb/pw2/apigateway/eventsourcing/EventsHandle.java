package io.github.ifpb.pw2.apigateway.eventsourcing;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pw2.EventMessage;
import io.github.pw2.ambienteservice.models.Ambiente;
import io.github.pw2.ambienteservice.services.AmbienteService;
import io.github.pw2.cursoservice.models.Curso;
import io.github.pw2.cursoservice.services.CursoService;
import io.github.pw2.horarioservice.models.HorarioAcademico;
import io.github.pw2.horarioservice.services.HorarioAcademicoService;
import io.github.pw2.professorservice.models.Professor;
import io.github.pw2.professorservice.services.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventsHandle {

    private final CursoService cursoService;
    private final AmbienteService ambienteService;
    private final ProfessorService professorService;
    private final HorarioAcademicoService horarioAcademicoService;

    private final ObjectMapper mapper = new ObjectMapper();

    public EventsHandle(CursoService cursoService, AmbienteService ambienteService, ProfessorService professorService, HorarioAcademicoService horarioAcademicoService) {
        this.cursoService = cursoService;
        this.ambienteService = ambienteService;
        this.professorService = professorService;
        this.horarioAcademicoService = horarioAcademicoService;
    }

    @StreamListener(target = Sink.INPUT)
    public void handleEventMessage(@Payload EventMessage message) {
        log.info("Received EventMessage: {}", message);

        switch (message.getServiceName()) {

            case HORARIOSERVICE:
                log.info("Processing Message as HORARIOSERVICE: {}", message);
                handleHorario(message);
                break;
            case AMBIENTESERVICE:
                log.info("Processing Message as AMBIENTESERVICE: {}", message);
                handleAmbiente(message);
                break;
            case PROFESSORSERVICE:
                log.info("Processing Message as PROFESSORSERVICE: {}", message);
                handleProfessor(message);
                break;
            case CURSOSERVICE:
                log.info("Processing Message as CURSOSERVICE: {}", message);
                handleCurso(message);
                break;
        }

    }

    private void handleHorario(EventMessage message) {

        HorarioAcademico horarioAcademico = null;

        try {
            horarioAcademico = mapper.convertValue(message.getPayload(), HorarioAcademico.class);

            if (horarioAcademico == null) throw new ClassCastException();

        } catch (Exception ex) {
            log.error("Invalid Payload on processing Message as HORARIOSERVICE: {}", message);

            ex.printStackTrace();
            return;
        }


        switch (message.getOperation()) {
            case PERSIST:
                this.horarioAcademicoService.salvarHorario(horarioAcademico);
                break;
            case DELETE: {
                this.horarioAcademicoService.deletar(horarioAcademico.getId());
                break;
            }
        }

    }

    private void handleProfessor(EventMessage message) {

        Professor professor = null;

        try {
            professor = mapper.convertValue(message.getPayload(), Professor.class);

            if (professor == null) throw new ClassCastException();

        } catch (Exception ex) {
            log.error("Invalid Payload on processing Message as PROFESSORSERVICE: {}", message);

            ex.printStackTrace();
            return;
        }


        switch (message.getOperation()) {
            case PERSIST:
                this.professorService.salvar(professor);
                break;
            case DELETE:
                this.professorService.deletar(professor.getMatricula());
                break;
            case UPDATE:
                this.professorService.atualizar(professor, professor.getMatricula());
                break;
        }

    }

    private void handleAmbiente(EventMessage message) {

        Ambiente ambiente = null;

        try {
            ambiente = mapper.convertValue(message.getPayload(), Ambiente.class);

            if (ambiente == null) throw new ClassCastException();

        } catch (Exception ex) {
            log.error("Invalid Payload on processing Message as AMBIENTESERVICE: {}", message);

            ex.printStackTrace();
            return;
        }

        switch (message.getOperation()) {
            case PERSIST:
                this.ambienteService.salvar(ambiente);
                break;
            case DELETE:
                this.ambienteService.remover(ambiente.getCodigo());
                break;
            case UPDATE:
                this.ambienteService.atualizar(ambiente, ambiente.getCodigo());
                break;
        }

    }

    private void handleCurso(EventMessage message) {

        Curso curso = null;

        try {
            curso = mapper.convertValue(message.getPayload(), Curso.class);

            if (curso == null) throw new ClassCastException();

        } catch (Exception ex) {
            log.error("Invalid Payload on processing Message as CURSOSERVICE: {}", message);

            ex.printStackTrace();
            return;
        }

        switch (message.getOperation()) {
            case PERSIST:
                Curso cursoPersistido = this.cursoService.salvar(curso);

                log.info("Course persisted for CQRS : " + cursoPersistido);

                break;
            case DELETE:
                this.cursoService.deletar(curso.getCodigo());
                break;
            case UPDATE:
                this.cursoService.atualizar(curso, curso.getCodigo());
                break;
        }

    }

}