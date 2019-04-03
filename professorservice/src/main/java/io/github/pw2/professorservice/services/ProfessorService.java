package io.github.pw2.professorservice.services;

import com.google.common.collect.ImmutableList;
import io.github.pw2.professorservice.models.Professor;
import io.github.pw2.professorservice.repositories.ProfessorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;
    private final Logger log = LogManager.getLogger(this.getClass());

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public Professor salvar(@NotNull Professor professor) {
        log.info("Salvando professor: " + professor.toString());
        return this.repository.save(professor);
    }

    public Professor atualizar(@NotNull Professor professorNovo, @NotNull String matricula) {

        log.info("Atualizando o professor com matricula = " + matricula);
        // Pode Lançar EntityNotFoundException caso nao exista este professor
        Professor professorDB = this.repository.getOne(matricula);

        professorDB.setNome(professorNovo.getNome());

        return this.repository.save(professorDB);
    }

    public void deletar(@NotNull String matricula) {

        log.info("Deletando professor com matricula = " + matricula);
        // Pode Lançar EntityNotFoundException caso nao exista este professor
        Professor professor = this.repository.getOne(matricula);

        this.repository.delete(professor);
    }

    public List<Professor> listarAll() {
        log.info("buscando todos os professores");
        return ImmutableList.copyOf(this.repository.findAll());
    }

    public Optional<Professor> buscarPorMatricula(@NotNull String matricula) {
        log.info("Buscando professor por matricula " + matricula);
        return this.repository.findById(matricula);
    }

    public Optional<Professor> buscarPorNome(@NotNull String nome) {
        log.info("Buscando professor por nome: " + nome);
        return this.repository.findByNome(nome);
    }

}
