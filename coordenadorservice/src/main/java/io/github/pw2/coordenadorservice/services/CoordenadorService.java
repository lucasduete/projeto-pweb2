package io.github.pw2.coordenadorservice.services;

import io.github.pw2.coordenadorservice.models.Coordenador;
import io.github.pw2.coordenadorservice.repositories.CoordenadorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class CoordenadorService {

    private final PasswordEncoder passwordEncoder;
    private final CoordenadorRepository repository;
    private Logger logger;

    public CoordenadorService(PasswordEncoder passwordEncoder, CoordenadorRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        logger = LogManager.getLogger(CoordenadorService.class);
    }

    public Coordenador salvar(Coordenador coordenador) {
        logger.info("Setando senha para o coordenador");
        coordenador.setSenha(passwordEncoder.encode(coordenador.getSenha()));

        logger.info("Salvando coordenador " + coordenador);
        return this.repository.save(coordenador);
    }

    public Coordenador atualizar(@NotNull Coordenador coordenadorNovo, @NotNull String matricula) {

        // Pode Lançar EntityNotFoundException caso nao exista este coordanador
        Coordenador coordenadorDB = this.repository.getOne(matricula);

        coordenadorDB.setNome(coordenadorNovo.getNome());
        coordenadorDB.setSenha(
                passwordEncoder.encode(coordenadorNovo.getSenha())
        );
        logger.info("Atualizando o coordenador para " + coordenadorNovo);
        return this.repository.save(coordenadorDB);
    }

    public void deletar(@NotNull String matricula) {

        // Pode Lançar EntityNotFoundException caso nao exista este coordanador
        Coordenador coordenador = this.repository.getOne(matricula);

        // Optou-se por esse metodo de delete ao inves de usar o deleteById para
        // tratar manualmente se a entidade existe ou nao no DB antes de remover
        logger.info("Deletando coordenador com matrícula " + matricula);
        this.repository.delete(coordenador);
    }

    public List<Coordenador> recuperarTodos() {
        logger.info("Listando todos os coordenadores");
        return repository.findAll();
    }

    public Optional<Coordenador> recuperar(String matricula) {
        logger.info("Buscando coordenador com a matrícula " + matricula);
        return repository.findByMatricula(matricula);
    }

}
