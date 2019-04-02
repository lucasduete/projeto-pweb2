package io.github.pw2.horarioservice.repositories;

import io.github.pw2.horarioservice.models.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    public Optional<List<Aula>> findAllByCodigoAmbiente(@NotNull final String codigoAmbiente);
    public Optional<List<Aula>> findAllByMatriculaProfessor(@NotNull final String matriculaProfessor);

}
