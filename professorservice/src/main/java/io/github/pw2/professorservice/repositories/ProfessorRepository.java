package io.github.pw2.professorservice.repositories;

import io.github.pw2.professorservice.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String> {

    public Optional<Professor> findByNome(String nome);

}
