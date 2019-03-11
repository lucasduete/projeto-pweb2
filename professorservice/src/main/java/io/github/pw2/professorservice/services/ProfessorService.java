package io.github.pw2.professorservice.services;

import io.github.pw2.professorservice.models.Professor;
import io.github.pw2.professorservice.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public Professor salvar(Professor professor) {
        return this.repository.save(professor);
    }

}
