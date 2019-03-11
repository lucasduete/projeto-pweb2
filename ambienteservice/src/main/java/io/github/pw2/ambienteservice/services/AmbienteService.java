package io.github.pw2.ambienteservice.services;

import com.google.common.collect.ImmutableList;
import io.github.pw2.ambienteservice.models.Ambiente;
import io.github.pw2.ambienteservice.repositories.AmbienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmbienteService {

    private final AmbienteRepository repository;

    public AmbienteService(AmbienteRepository repository) {
        this.repository = repository;
    }

    public Ambiente salvar(Ambiente ambiente) {
        return this.repository.save(ambiente);
    }

    public List<Ambiente> listarTodos() {
        return ImmutableList.copyOf(this.repository.findAll());
    }

    public Optional<Ambiente> recuperarPorCodigo(String codigo) {
        return this.repository.findById(codigo);
    }

    public Optional<Ambiente> recuperarPorNome(String nome) {
        return this.repository.findByNome(nome);
    }

}
