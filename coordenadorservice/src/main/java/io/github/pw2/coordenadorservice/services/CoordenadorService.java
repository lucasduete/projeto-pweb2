package io.github.pw2.coordenadorservice.services;

import io.github.pw2.coordenadorservice.models.Coordenador;
import io.github.pw2.coordenadorservice.repositories.CoordenadorRepository;
import org.springframework.stereotype.Service;

@Service
public class CoordenadorService {

    private final CoordenadorRepository repository;

    public CoordenadorService(CoordenadorRepository repository) {
        this.repository = repository;
    }

    public Coordenador salvar(Coordenador coordenador) {
        return this.repository.save(coordenador);
    }

}
