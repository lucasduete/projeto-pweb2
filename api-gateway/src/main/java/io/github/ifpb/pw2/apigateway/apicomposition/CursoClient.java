package io.github.ifpb.pw2.apigateway.apicomposition;

import io.github.pw2.cursoservice.models.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cursoservice", path = "/curso")
public interface CursoClient {

    @PostMapping
    ResponseEntity salvarCursoDisciplina(@RequestBody Curso curso);

}
