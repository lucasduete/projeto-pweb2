package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.AmbienteClient;
import io.github.pw2.ambienteservice.models.Ambiente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ambiente")
public class AmbienteController {

    private final AmbienteClient ambienteClient;

    public AmbienteController(AmbienteClient ambienteClient) {
        this.ambienteClient = ambienteClient;
    }

    public ResponseEntity salvarAmbiente(@RequestBody Ambiente ambiente) {
        return this.ambienteClient.salvarAmbiente(ambiente);
    }

}
