package io.github.pw2.cursoservice.services;

import io.github.pw2.cursoservice.models.Disciplina;
import io.github.pw2.cursoservice.repositories.DisciplinaRepository;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    private final DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public Disciplina salvar(Disciplina disciplina) {
        return this.repository.save(disciplina);
    }

}
