package io.github.pw2.coordenadorservice.controllers;

import io.github.pw2.coordenadorservice.models.Coordenador;
import io.github.pw2.coordenadorservice.services.CoordenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("coordenador")
public class CoordenadorController {

    private final CoordenadorService service;
    private Logger logger;

    public CoordenadorController(CoordenadorService service) {
        this.service = service;
        logger = LogManager.getLogger(CoordenadorController.class);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveCoordenador(@RequestBody Coordenador coordenador) {

        // Verifica se o coordenador e nulo e se algum de seus atributos sao nulos

        if (coordenador == null) {
            return ResponseEntity.badRequest().body(logError("Nao foi enviado nenhum coordenador para ser persistido."));
        } else if (coordenador.getMatricula() == null) {
            return ResponseEntity.badRequest().body(logError("E necessario enviar uma matricula valida para que o coordenador seja persistido."));
        } else if (coordenador.getNome() == null || coordenador.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body(logError("E necessario enviar um nome valido para que o coordenador seja persistido."));
        } else if (coordenador.getSenha() == null || coordenador.getSenha().isEmpty()) {
            return ResponseEntity.badRequest().body(logError("E necessario enviar uma senha valida para que o coordenador seja persistido."));
        }

        // Se nada for null entao pode-se persistir o ambiente

        Coordenador coordenadorSalvo = this.service.salvar(coordenador);

        if (coordenadorSalvo == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(logError("Nao foi possivel persitir esta entidade."));
        } else {
            return ResponseEntity.ok(coordenador);

        }
    }

    @GetMapping
    public ResponseEntity<List<Coordenador>> recuperarTodos() {
        List<Coordenador> coordenadores = service.recuperarTodos();
        return coordenadores.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(coordenadores);
    }

    @GetMapping(value = "/{matricula}")
    public ResponseEntity<Coordenador> recuperar(@PathVariable("matricula") String matricula) {
        Optional<Coordenador> coordenador = service.recuperar(matricula);
        return coordenador.isPresent() ? ResponseEntity.ok(coordenador.get()) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{matricula}")
    private ResponseEntity atualizarCoordenador(@PathVariable(name = "matricula", required = true) String matricula,
                                                @RequestBody Coordenador coordenador) {

        if (matricula == null || matricula.isEmpty()) {
            return ResponseEntity.badRequest().body(logError("Voce deve informar uma matricula valida"));

        } else if (coordenador == null) {
            return ResponseEntity.badRequest().body(logError("Voce deve informar os dados do coordenador para que sejam atualizados."));

        } else if (coordenador.getNome() == null || coordenador.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body(logError("Voce deve informar um nome valido para o coordenador"));

        } else if (coordenador.getSenha() == null || coordenador.getSenha().isEmpty()) {
            return ResponseEntity.badRequest().body(logError("Voce deve informar uma senha valida para o coordenador"));
        }

        try {
            Coordenador coordenadorAtualiado = this.service.atualizar(coordenador, matricula);
            return ResponseEntity.ok(coordenadorAtualiado);

        } catch (EntityNotFoundException enfEx) {
            enfEx.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

    }

    @DeleteMapping("/{matricula}")
    private ResponseEntity deletarCoordenador(@PathVariable(name = "matricula", required = true) String matricula) {

        if (matricula == null || matricula.isEmpty())
            return ResponseEntity.badRequest().body(logError("Voce deve informar uma matricula valida."));

        try {

            this.service.deletar(matricula);
            return ResponseEntity.ok().build();

        } catch (EntityNotFoundException enfEx) {
            enfEx.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

    }

    private String logError(String msg) {
        logger.error(msg);
        return msg;
    }
}
