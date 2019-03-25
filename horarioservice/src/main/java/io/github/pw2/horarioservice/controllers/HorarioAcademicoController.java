package io.github.pw2.horarioservice.controllers;

import io.github.pw2.horarioservice.models.HorarioAcademico;
import io.github.pw2.horarioservice.services.HorarioAcademicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("horarioacademico")
public class HorarioAcademicoController {

    private final HorarioAcademicoService service;

    public HorarioAcademicoController(HorarioAcademicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity salvarHorario(@RequestBody HorarioAcademico horarioAcademico) {

        this.service.salvarHorario(horarioAcademico);
    }

}
