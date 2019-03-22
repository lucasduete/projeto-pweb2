package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.AmbienteClient;
import io.github.pw2.ambienteservice.models.Ambiente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ambiente")
@Api(tags = "Gerenciamento de Ambientes")
public class AmbienteController {

    private final AmbienteClient ambienteClient;

    public AmbienteController(AmbienteClient ambienteClient) {
        this.ambienteClient = ambienteClient;
    }

    @PostMapping
    @ApiOperation("Serviço de criação de ambientes")
    public ResponseEntity salvarAmbiente(@RequestBody @ApiParam("Ambiente a ser criado") Ambiente ambiente) {
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
