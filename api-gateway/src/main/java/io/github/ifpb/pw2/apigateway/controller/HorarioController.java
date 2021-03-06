package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.service.HorarioServiceComposition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("horariocurso")
public class HorarioController {

    private final HorarioServiceComposition service;

    public HorarioController(HorarioServiceComposition service) {
        this.service = service;
    }

    @GetMapping("/curso/{codigoCurso}")
    private ResponseEntity buscarPorCurso(@PathVariable("codigoCurso") Long codCurso) {
        return ResponseEntity.ok(service.buscarPorCurso(codCurso));
    }

}
