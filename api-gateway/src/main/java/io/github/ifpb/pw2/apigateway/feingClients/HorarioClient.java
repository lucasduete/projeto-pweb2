package io.github.ifpb.pw2.apigateway.feingClients;

import io.github.pw2.horarioservice.models.HorarioAcademico;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "horarioservice", path = "/horario")
public interface HorarioClient {

    @GetMapping("/curso/{codigoCurso}")
    public ResponseEntity<List<HorarioAcademico>> buscarPorCurso(
            @PathVariable(name = "codigoCurso", required = true)
                    Long codigoCurso);
}
