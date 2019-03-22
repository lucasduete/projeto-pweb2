package io.github.pw2.coordenadorservice.services;

import io.github.pw2.coordenadorservice.models.Coordenador;
import io.github.pw2.coordenadorservice.repositories.CoordenadorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordenadorService {

    private final PasswordEncoder passwordEncoder;

    private final CoordenadorRepository repository;

    public CoordenadorService(PasswordEncoder passwordEncoder, CoordenadorRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public Coordenador salvar(Coordenador coordenador) {
        coordenador.setSenha(passwordEncoder.encode(coordenador.getSenha()));
        return this.repository.save(coordenador);
    }

    public List<Coordenador> recuperarTodos(){
        return repository.findAll();
    }

    public Optional<Coordenador> recuperar(String matricula){
        return repository.findByMatricula(matricula);
    }

}
