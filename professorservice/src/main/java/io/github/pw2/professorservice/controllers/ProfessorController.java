package io.github.pw2.professorservice.controllers;

import io.github.pw2.EventMessage;
import io.github.pw2.professorservice.models.Professor;
import io.github.pw2.professorservice.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("professor")
public class ProfessorController {

    private final ProfessorService service;

    @Output(Source.OUTPUT)
    @Autowired
    private MessageChannel messageChannel;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity saveProfessor(@RequestBody Professor professor) {

        // Verifica se o professor e nulo e se algum de seus atributos sao nulos

        if (professor == null) {
            return ResponseEntity.badRequest().body("Nao foi enviado nenhum professor para ser persistido.");
        } else if (professor.getMatricula() == null) {
            return ResponseEntity.badRequest().body("E necessario enviar uma matricula valida para que o professor seja persistido.");
        } else if (professor.getNome() == null || professor.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("E necessario enviar um nome valido para que o professor seja persistido.");
        }

        // Se nada for null entao pode-se persistir o ambiente

        Professor professorSalvo = this.service.salvar(professor);

        if (professorSalvo == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nao foi possivel persitir esta entidade.");
        } else {

            this.messageChannel.send(
                    MessageBuilder.withPayload(
                            EventMessage
                                    .builder()
                                    .serviceName(EventMessage.ServiceType.PROFESSORSERVICE)
                                    .operation(EventMessage.Operation.PERSIST)
                                    .payload(professorSalvo)
                                    .build()
                    ).build()
            );

            return ResponseEntity.ok(professor);
        }

    }

    @GetMapping
    public ResponseEntity listarTodos() {
        List<Professor> professores = this.service.listarAll();

        if (professores == null || professores.size() == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(professores);
        }
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Professor> buscarPorMatricula(@PathVariable("matricula") String matricula) {

        if (matricula == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Professor> professor = this.service.buscarPorMatricula(matricula);

        return professor.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity buscarPorNome(@RequestParam(name = "nome", required = true) String nome) {

        if (nome == null || nome.isEmpty()) {
            return ResponseEntity.badRequest().body("E necessario fornecer o Nome para concluir esta requisicao");
        }

        Optional<Professor> professor = this.service.buscarPorNome(nome);

        return professor.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/{matricula}")
    private ResponseEntity atualizarProfessor(@PathVariable(name = "matricula", required = true) String matricula,
                                              @RequestBody Professor professor) {

        if (matricula == null || matricula.isEmpty()) {
            return ResponseEntity.badRequest().body("Voce deve informar uma matricula valida");

        } else if (professor == null) {
            return ResponseEntity.badRequest().body("Voce deve informar os dados do professor para que sejam atualizados.");

        } else if (professor.getNome() == null || professor.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("Voce deve informar um nome valido para o professor");
        }

        try {

            Professor professorAtualizado = this.service.atualizar(professor, matricula);

            this.messageChannel.send(
                    MessageBuilder.withPayload(
                            EventMessage
                                    .builder()
                                    .serviceName(EventMessage.ServiceType.PROFESSORSERVICE)
                                    .operation(EventMessage.Operation.UPDATE)
                                    .payload(professorAtualizado)
                                    .build()
                    ).build()
            );

            return ResponseEntity.ok(professorAtualizado);

        } catch (EntityNotFoundException enfEx) {
            enfEx.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

    }

    @DeleteMapping("/{matricula}")
    private ResponseEntity deletarProfessor(@PathVariable(name = "matricula", required = true) String matricula) {

        if (matricula == null || matricula.isEmpty())
            return ResponseEntity.badRequest().body("Voce deve informar uma matricula valida.");

        try {

            this.service.deletar(matricula);

            Professor professor = new Professor(matricula, null);

            this.messageChannel.send(
                    MessageBuilder.withPayload(
                            EventMessage
                                    .builder()
                                    .serviceName(EventMessage.ServiceType.PROFESSORSERVICE)
                                    .operation(EventMessage.Operation.DELETE)
                                    .payload(professor)
                                    .build()
                    ).build()
            );

            return ResponseEntity.ok().build();

        } catch (EntityNotFoundException enfEx) {
            enfEx.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

    }


}
