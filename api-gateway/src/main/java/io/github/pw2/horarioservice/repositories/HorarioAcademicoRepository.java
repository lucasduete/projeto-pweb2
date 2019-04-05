package io.github.pw2.horarioservice.repositories;

import io.github.pw2.horarioservice.models.HorarioAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HorarioAcademicoRepository extends JpaRepository<HorarioAcademico, Long> {

    public Optional<List<HorarioAcademico>> findAllByCodigoCurso(Long codigoCurso);

}
