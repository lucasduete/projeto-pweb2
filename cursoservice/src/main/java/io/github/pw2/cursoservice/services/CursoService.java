package io.github.pw2.cursoservice.services;

import io.github.pw2.cursoservice.models.Curso;
import io.github.pw2.cursoservice.repositories.CursoRepository;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public Curso salvar(Curso curso) {
        return this.repository.save(curso);
    }

}
