package io.github.pw2.cursoservice.repositories;

import io.github.pw2.cursoservice.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    public Optional<Curso> findByNome(String nome);

}
