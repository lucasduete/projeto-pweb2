package io.github.pw2.horarioservice.services;

import com.google.common.collect.ImmutableList;
import io.github.pw2.horarioservice.exceptions.CursoSemHorarioAcademicoException;
import io.github.pw2.horarioservice.models.HorarioAcademico;
import io.github.pw2.horarioservice.repositories.HorarioAcademicoRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioAcademicoService {

    private final HorarioAcademicoRepository repository;

    public HorarioAcademicoService(HorarioAcademicoRepository repository) {
        this.repository = repository;
    }

    public HorarioAcademico salvarHorario(@NotNull final HorarioAcademico horarioAcademico) {

        return this.repository.save(horarioAcademico);
    }

    public List<HorarioAcademico> listarPorCurso(@NotNull final Long codigoCurso) throws CursoSemHorarioAcademicoException {

        Optional<List<HorarioAcademico>> optionalList = this.repository.findAllByCodigoCurso(codigoCurso);

        if (!optionalList.isPresent()) throw new CursoSemHorarioAcademicoException();

        return ImmutableList.copyOf(optionalList.get());
    }

    public List<HorarioAcademico> listarPorProfessor(String matriculaProfessor) {
        // todo
        return new ArrayList<>();
    }

    public List<HorarioAcademico> listarPorAmbiente() {
        // todo
        return new ArrayList<>();
    }

    public void deletar(Long id){
        Optional<HorarioAcademico> horarioAcademico = repository.findById(id);
        if(horarioAcademico.isPresent()){
            repository.delete(horarioAcademico.get());
        }
    }

}
