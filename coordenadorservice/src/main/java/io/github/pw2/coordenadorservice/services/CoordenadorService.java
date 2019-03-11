package io.github.pw2.coordenadorservice.services;

import io.github.pw2.coordenadorservice.models.Coordenador;
import io.github.pw2.coordenadorservice.repositories.CoordenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordenadorService {

    private final CoordenadorRepository repository;

    public CoordenadorService(CoordenadorRepository repository) {
        this.repository = repository;
    }

    public Coordenador salvar(Coordenador coordenador) {
        return this.repository.save(coordenador);
    }

    public List<Coordenador> recuperarTodos(){
        return repository.findAll();
    }

    public Optional<Coordenador> recuperar(Long matricula){
        return repository.findById(matricula);
    }

}
