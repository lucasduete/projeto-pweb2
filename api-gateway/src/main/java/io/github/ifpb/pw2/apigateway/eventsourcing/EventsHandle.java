package io.github.ifpb.pw2.apigateway.eventsourcing;

import io.github.pw2.EventMessage;
import io.github.pw2.ambienteservice.models.Ambiente;
import io.github.pw2.ambienteservice.services.AmbienteService;
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

    private final AmbienteService ambienteService;
    private final ProfessorService professorService;
    private final HorarioAcademicoService horarioAcademicoService;

    public EventsHandle(AmbienteService ambienteService, ProfessorService professorService, HorarioAcademicoService horarioAcademicoService) {
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
        }

    }

    private void handleHorario(EventMessage message) {

        HorarioAcademico horarioAcademico = null;

        try {
            horarioAcademico = (HorarioAcademico) message.getPayload();

            if (horarioAcademico == null) throw new ClassCastException();

        } catch (ClassCastException ccEx) {
            log.error("Invalid Payload on processing Message as HORARIOSERVICE: {}", message);
            ccEx.printStackTrace();
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
            professor = (Professor) message.getPayload();

            if (professor == null) throw new ClassCastException();

        } catch (ClassCastException ccEx) {
            log.error("Invalid Payload on processing Message as PROFESSORSERVICE: {}", message);
            ccEx.printStackTrace();
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
            ambiente = (Ambiente) message.getPayload();

            if (ambiente == null) throw new ClassCastException();

        } catch (ClassCastException ccEx) {
            log.error("Invalid Payload on processing Message as AMBIENTESERVICE: {}", message);
            ccEx.printStackTrace();
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

}
