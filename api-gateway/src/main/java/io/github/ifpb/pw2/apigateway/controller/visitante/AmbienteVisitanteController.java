package io.github.ifpb.pw2.apigateway.controller.visitante;

import io.github.pw2.ambienteservice.models.Ambiente;
import io.github.pw2.ambienteservice.services.AmbienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("visitante/ambiente")
public class AmbienteVisitanteController {

    private final AmbienteService ambienteService;

    public AmbienteVisitanteController(AmbienteService ambienteService) {
        this.ambienteService = ambienteService;
    }

    @GetMapping
    public ResponseEntity listarAmbientes() {

        List<Ambiente> ambientes = this.ambienteService.listarTodos();

        if (ambientes == null || ambientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(ambientes);
        }

    }

}
