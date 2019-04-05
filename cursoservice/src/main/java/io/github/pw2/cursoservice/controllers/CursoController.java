package io.github.pw2.cursoservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pw2.EventMessage;
import io.github.pw2.cursoservice.models.Curso;
import io.github.pw2.cursoservice.models.Disciplina;
import io.github.pw2.cursoservice.services.CursoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@RequestMapping("curso")
public class CursoController {

    private final CursoService service;
    private Logger logger;

    @Output(Source.OUTPUT)
    @Autowired
    private MessageChannel messageChannel;

    public CursoController(CursoService service) {
        this.service = service;
        logger = LogManager.getLogger(CursoController.class);
    }

    @PostMapping
    public ResponseEntity saveCursoDisciplinas(@RequestBody Curso curso) throws JsonProcessingException, CloneNotSupportedException {

        logger.info("Tentativa de salvar um novo Curso");
        // Este unico EndPoint deve ser responsavel por persistir curso e Disciplina
        // Verifica se o curso e nulo e se algum de seus atributos sao nulos

        if (curso == null) {
            return ResponseEntity.badRequest().body(logError("Nao foi enviado nenhum curso para ser persistido."));
        } else if (curso.getCodigo() == null) {
            return ResponseEntity.badRequest().body(logError("E necessario enviar uma código válido para que o curso seja persistido."));
        } else if (curso.getNome() == null || curso.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body(logError("E necessario enviar um nome valido para que o curso seja persistido."));
        } else if (curso.getDescricao() == null || curso.getDescricao().isEmpty()) {
            return ResponseEntity.badRequest().body(logError("E necessario enviar uma descricao valida para que o curso seja persistido."));
        }

        // Verifica se as disciplinas sao nulas e se algum de seus atributos sao nulos
        if (curso.getDisciplinas() == null) {
            logger.info("Tentando persistir curso sem disciplinas, CodigoCurso = " + curso.getCodigo());
//            return ResponseEntity.badRequest().body(logError("E necessario enviar as disciplinas do curso para que ele seja persistido."));
        } else if (verificaDisciplinasValidas(curso.getDisciplinas())) {
            return ResponseEntity.badRequest().body(logError("Uma ou mais Disciplinas enviadas sao invalidas, verifique os atributos e tente novamente."));
        }

        // Se nada for null entao pode-se persistir o curso
        Curso cursoSalvo = this.service.salvar(curso);

        if (cursoSalvo == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(logError("Nao foi possivel persitir esta entidade."));
        } else {
            logger.info("Curso salvo: " + cursoSalvo.getCodigo());

            Curso cursoClone = cursoSalvo.clone();
            cursoClone.getDisciplinas().forEach(disciplina -> disciplina.setCurso(null));

            System.out.println(cursoClone);
            this.messageChannel.send(
                    MessageBuilder.withPayload(
                            EventMessage
                                    .builder()
                                    .serviceName(EventMessage.ServiceType.CURSOSERVICE)
                                    .operation(EventMessage.Operation.PERSIST)
                                    .payload(cursoClone)
                                    .build()
                    ).build()
            );

            return ResponseEntity.ok(cursoSalvo);
        }

    }

    @GetMapping
    public ResponseEntity listarTodos() {
        List<Curso> cursos = this.service.listarAll();

        if (cursos == null || cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cursos);
        }

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Curso> buscarPorCodigo(@PathVariable(name = "codigo", required = true) Long codigo) {

        Optional<Curso> curso = this.service.buscarPorCodigo(codigo);

        return curso.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity buscarPorNome(@RequestParam(name = "nome", required = true) String nome) {

        Optional<Curso> curso = this.service.buscarPorNome(nome);

        return curso.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/{codigoCurso}")
    private ResponseEntity atualizarCurso(@PathVariable(name = "codigoCurso", required = true) Long codigoCurso,
                                          @RequestBody Curso curso) {

        if (codigoCurso == null || codigoCurso <= 0)
            return ResponseEntity.badRequest().body(logError("Voce deve informar um codigo de curso valido"));

        if (curso == null || ((curso.getNome() == null || curso.getNome().isEmpty()) &&
                (curso.getDescricao() == null || curso.getDescricao().isEmpty()) &&
                (curso.getDisciplinas() == null || curso.getDisciplinas().isEmpty()))) {
            return ResponseEntity.badRequest().body(logError("Voce deve informar os dados do curso que devem ser atualizados"));

        }

        try {

            Curso cursoAtualizado = this.service.atualizar(curso, codigoCurso);

            this.messageChannel.send(
                    MessageBuilder.withPayload(
                            EventMessage
                                    .builder()
                                    .serviceName(EventMessage.ServiceType.CURSOSERVICE)
                                    .operation(EventMessage.Operation.UPDATE)
                                    .payload(cursoAtualizado)
                                    .build()
                    ).build()
            );

            return ResponseEntity.ok(cursoAtualizado);

        } catch (EntityNotFoundException enfEx) {
            enfEx.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

    }

    @DeleteMapping("/{codigoCurso}")
    private ResponseEntity deletarCurso(@PathVariable(name = "codigoCurso", required = true) Long codigoCurso) {

        if (codigoCurso == null || codigoCurso <= 0)
            return ResponseEntity.badRequest().body(logError("Voce deve informar um codigo de curso valido"));

        try {

            this.service.deletar(codigoCurso);

            Curso curso = new Curso();
            curso.setCodigo(codigoCurso);

            this.messageChannel.send(
                    MessageBuilder.withPayload(
                            EventMessage
                                    .builder()
                                    .serviceName(EventMessage.ServiceType.CURSOSERVICE)
                                    .operation(EventMessage.Operation.DELETE)
                                    .payload(curso)
                                    .build()
                    ).build()
            );

            return ResponseEntity.ok().build();

        } catch (EntityNotFoundException enfEx) {
            enfEx.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

    }

    private boolean verificaDisciplinasValidas(List<Disciplina> disciplinas) {

        Optional<Disciplina> anyDisciplinaInvalida = disciplinas
                .stream()
                .filter(disciplina -> (disciplina.getCodigo() == null || disciplina.getNome() == null || disciplina.getCodigo() == null))
                .findAny();

        return anyDisciplinaInvalida.isPresent();
    }

    private String logError(String msg) {
        logger.error(msg);
        return msg;
    }

}
