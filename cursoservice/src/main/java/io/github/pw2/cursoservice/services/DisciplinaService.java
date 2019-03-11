package io.github.pw2.cursoservice.services;

import com.google.common.collect.ImmutableList;
import io.github.pw2.cursoservice.models.Disciplina;
import io.github.pw2.cursoservice.repositories.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private final DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public List<Disciplina> listarAll() {
        return ImmutableList.copyOf(this.repository.findAll());
    }

    public Optional<Disciplina> buscarPorCodigo(Long codigo) {
        return this.repository.findById(codigo);
    }

    public Optional<Disciplina> buscarPorNome(String nome) {
        return this.repository.findByNome(nome);
    }

}
