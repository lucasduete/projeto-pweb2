package io.github.pw2.ambienteservice.services;

import io.github.pw2.ambienteservice.models.Ambiente;
import io.github.pw2.ambienteservice.repositories.AmbienteRepository;
import org.springframework.stereotype.Service;

@Service
public class AmbienteService {

    private final AmbienteRepository repository;

    public AmbienteService(AmbienteRepository repository) {
        this.repository = repository;
    }

    public Ambiente salvar(Ambiente ambiente) {
        return this.repository.save(ambiente);
    }

}
