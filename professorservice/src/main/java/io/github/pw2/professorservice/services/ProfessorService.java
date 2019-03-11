package io.github.pw2.professorservice.services;

import com.google.common.collect.ImmutableList;
import io.github.pw2.professorservice.models.Professor;
import io.github.pw2.professorservice.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public Professor salvar(Professor professor) {
        return this.repository.save(professor);
    }

    public List<Professor> listarAll() {
        return ImmutableList.copyOf(this.repository.findAll());
    }

    public Optional<Professor> buscarPorMatricula(Long matricula) {
        return this.repository.findById(matricula);
    }

    public Optional<Professor> buscarPorNome(String nome) {
        return this.repository.findByNome(nome);
    }

}
