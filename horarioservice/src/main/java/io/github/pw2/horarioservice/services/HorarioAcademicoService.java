package io.github.pw2.horarioservice.services;

import io.github.pw2.horarioservice.models.HorarioAcademico;
import io.github.pw2.horarioservice.repositories.HorarioAcademicoRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class HorarioAcademicoService {

    private final HorarioAcademicoRepository repository;


    public HorarioAcademicoService(HorarioAcademicoRepository repository) {
        this.repository = repository;
    }

    public HorarioAcademico salvar(@NotNull final HorarioAcademico horarioAcademico) {

        return this.repository.save(horarioAcademico);
    }

}
