package io.github.ifpb.pw2.apigateway.feingClients;

import io.github.pw2.ambienteservice.models.Ambiente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ambienteservice", path = "/ambiente")
public interface AmbienteClient {

    @PostMapping
    ResponseEntity salvarAmbiente(@RequestBody Ambiente ambiente);

}
