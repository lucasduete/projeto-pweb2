package io.github.ifpb.pw2.apigateway.feingClients;

import io.github.pw2.coordenadorservice.models.Coordenador;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "coordenadorservice")
public interface CoordenadorClient {

    @GetMapping(value = "/{matricula}")
    public ResponseEntity<Coordenador> recuperar(@PathVariable("matricula") Long matricula);
}
