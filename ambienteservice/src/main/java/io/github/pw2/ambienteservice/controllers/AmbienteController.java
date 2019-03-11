package io.github.pw2.ambienteservice.controllers;

import io.github.pw2.ambienteservice.models.Ambiente;
import io.github.pw2.ambienteservice.services.AmbienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ambiente")
public class AmbienteController {

    private final AmbienteService service;

    public AmbienteController(AmbienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity saveAmbiente(@RequestBody Ambiente ambiente) {

        // Verifica se o ambiente e nulo e se algum de seus atributos sao nulos

        if (ambiente == null)
            return ResponseEntity.badRequest().body("Nao foi enviado nenhum ambiente para ser persistido.");
        else if (ambiente.getCodigo() == null || ambiente.getNome() == null) {
            return ResponseEntity.badRequest().body("E necessario enviar todos os atributos da entidade ambiente para que ele seja persistido.");
        }

        // Se nada for null entao pode-se persistir o ambiente

        Ambiente ambientePersistido = this.service.salvar(ambiente);

        if (ambientePersistido == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nao foi possivel persitir esta entidade.");
        } else {
            return ResponseEntity.ok(ambiente);
        }

    }

}
