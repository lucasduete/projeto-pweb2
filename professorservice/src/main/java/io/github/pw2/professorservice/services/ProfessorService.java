package io.github.pw2.professorservice.services;

import com.google.common.collect.ImmutableList;
import io.github.pw2.professorservice.models.Professor;
import io.github.pw2.professorservice.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public Professor salvar(@NotNull Professor professor) {
        return this.repository.save(professor);
    }

    public Professor atualizar(@NotNull Professor professorNovo, @NotNull String matricula) {

        // Pode Lançar EntityNotFoundException caso nao exista este professor
        Professor professorDB = this.repository.getOne(matricula);

        professorDB.setNome(professorNovo.getNome());

        return this.repository.save(professorDB);
    }

    public void deletar(@NotNull String matricula) {

        // Pode Lançar EntityNotFoundException caso nao exista este professor
        Professor professor = this.repository.getOne(matricula);

        this.repository.delete(professor);
    }

    public List<Professor> listarAll() {
        return ImmutableList.copyOf(this.repository.findAll());
    }

    public Optional<Professor> buscarPorMatricula(@NotNull String matricula) {
        return this.repository.findById(matricula);
    }

    public Optional<Professor> buscarPorNome(@NotNull String nome) {
        return this.repository.findByNome(nome);
    }

}
