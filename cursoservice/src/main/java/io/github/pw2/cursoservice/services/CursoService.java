package io.github.pw2.cursoservice.services;

import com.google.common.collect.ImmutableList;
import io.github.pw2.cursoservice.models.Curso;
import io.github.pw2.cursoservice.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public Curso salvar(Curso curso) {

        curso.getDisciplinas().forEach(disciplina -> disciplina.setCurso(curso));

        return this.repository.save(curso);
    }

    public List<Curso> listarAll() {
        return ImmutableList.copyOf(this.repository.findAll());
    }

    public Optional<Curso> buscarPorCodigo(Long codigo) {
        return this.repository.findById(codigo);
    }

    public Optional<Curso> buscarPorNome(String nome) {
        return this.repository.findByNome(nome);
    }

}
