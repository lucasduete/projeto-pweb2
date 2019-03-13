package io.github.ifpb.pw2.apigateway.feingClients;

import io.github.pw2.ambienteservice.models.Ambiente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "ambienteservice", path = "/ambiente")
public interface AmbienteClient {

    @PostMapping
    ResponseEntity<?> salvarAmbiente(@RequestBody Ambiente ambiente);

    @GetMapping
    ResponseEntity<?> listarTodos();

    @GetMapping("/{codigo}")
    ResponseEntity<?> buscarPorCodigo(@PathVariable("codigo") String codigo);

    @GetMapping("/buscar")
    ResponseEntity<?> buscarPorNome(@RequestParam(name = "nome", required = true) String nome);

}
