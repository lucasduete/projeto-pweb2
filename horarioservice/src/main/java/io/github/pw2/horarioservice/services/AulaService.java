package io.github.pw2.horarioservice.services;

import com.google.common.collect.ImmutableList;
import io.github.pw2.horarioservice.models.Aula;
import io.github.pw2.horarioservice.repositories.AulaRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;

    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    public List<Aula> findAllByCodigoAmbiente(@NotNull String codigoAmbiente) {

        if (codigoAmbiente == null || codigoAmbiente.isEmpty()) return ImmutableList.of();

        Optional<List<Aula>> aulasDoAmbiente = this.aulaRepository.findAllByCodigoAmbiente(codigoAmbiente);

        if (!aulasDoAmbiente.isPresent()) return ImmutableList.of();
        else return ImmutableList.copyOf(aulasDoAmbiente.get());

    }

    public List<Aula> findAllByMatriculaProfessor(@NotNull final String matriculaProfessor) {

        if (matriculaProfessor == null || matriculaProfessor.isEmpty()) return ImmutableList.of();

        Optional<List<Aula>> aulasDoProfessor = this.aulaRepository.findAllByMatriculaProfessor(matriculaProfessor);

        if (!aulasDoProfessor.isPresent()) return ImmutableList.of();
        else return ImmutableList.copyOf(aulasDoProfessor.get());

    }

}
