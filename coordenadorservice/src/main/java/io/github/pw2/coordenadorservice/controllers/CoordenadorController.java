package io.github.pw2.coordenadorservice.controllers;

import io.github.pw2.coordenadorservice.models.Coordenador;
import io.github.pw2.coordenadorservice.services.CoordenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coordenador")
public class CoordenadorController {

    private final CoordenadorService service;

    public CoordenadorController(CoordenadorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> saveCoordenador(@RequestBody Coordenador coordenador) {

        // Verifica se o coordenador e nulo e se algum de seus atributos sao nulos

        if (coordenador == null) {
            return ResponseEntity.badRequest().body("Nao foi enviado nenhum coordenador para ser persistido.");
        } else if (coordenador.getMatricula() == null) {
            return ResponseEntity.badRequest().body("E necessario enviar uma matricula valida para que o coordenador seja persistido.");
        } else if (coordenador.getNome() == null || coordenador.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("E necessario enviar um nome valido para que o coordenador seja persistido.");
        } else if (coordenador.getSenha() == null || coordenador.getSenha().isEmpty()) {
            return ResponseEntity.badRequest().body("E necessario enviar uma senha valida para que o coordenador seja persistido.");
        }

        // Se nada for null entao pode-se persistir o ambiente

        Coordenador coordenadorSalvo = this.service.salvar(coordenador);

        if (coordenadorSalvo == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nao foi possivel persitir esta entidade.");
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

}
