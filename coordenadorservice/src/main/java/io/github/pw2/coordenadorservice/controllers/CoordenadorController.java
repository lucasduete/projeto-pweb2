package io.github.pw2.coordenadorservice.controllers;

import io.github.pw2.coordenadorservice.models.Coordenador;
import io.github.pw2.coordenadorservice.services.CoordenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ambiente")
public class CoordenadorController {

    private final CoordenadorService service;

    public CoordenadorController(CoordenadorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity saveCoordenador(@RequestBody Coordenador coordenador) {

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

}
