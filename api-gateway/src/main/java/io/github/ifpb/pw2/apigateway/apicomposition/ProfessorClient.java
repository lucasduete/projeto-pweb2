package io.github.ifpb.pw2.apigateway.apicomposition;

import io.github.pw2.professorservice.models.Professor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "professorservice", path = "/professor")
public interface ProfessorClient {

    @PostMapping
    ResponseEntity salvarProfessor(@RequestBody Professor professor);

}
