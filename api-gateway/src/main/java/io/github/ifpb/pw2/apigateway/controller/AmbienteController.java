package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.AmbienteClient;
import io.github.pw2.ambienteservice.models.Ambiente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("ambiente")
public class AmbienteController {

    private final AmbienteClient ambienteClient;

    public AmbienteController(AmbienteClient ambienteClient) {
        this.ambienteClient = ambienteClient;
    }

    @PostMapping
    public ResponseEntity salvarAmbiente(@RequestBody Ambiente ambiente) {
        ResponseEntity response = this.ambienteClient.salvarAmbiente(ambiente);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping
    public ResponseEntity listarTodos() {
        ResponseEntity response = this.ambienteClient.listarTodos();

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarPorCodigo(@PathVariable("codigo") String codigo) {
        ResponseEntity response = this.ambienteClient.buscarPorCodigo(codigo);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/buscar")
    public ResponseEntity buscarPorNome(@RequestParam(name = "nome", required = true) String nome) {
        ResponseEntity response = this.ambienteClient.buscarPorNome(nome);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
