package io.github.pw2.cursoservice.services;

import com.google.common.collect.ImmutableList;
import io.github.pw2.cursoservice.models.Disciplina;
import io.github.pw2.cursoservice.repositories.DisciplinaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private final DisciplinaRepository repository;
    private final Logger log = LogManager.getLogger(this.getClass());

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public Disciplina atualizar(@NotNull final Disciplina disciplinaNova, @NotNull final Long codigoDisciplina) {

        // Pode Lançar EntityNotFoundException caso nao exista este curso
        Disciplina disciplinaDB = this.repository.getOne(codigoDisciplina);

        if (disciplinaNova.getNome() != null && !disciplinaNova.getNome().isEmpty()) {
            disciplinaDB.setNome(disciplinaDB.getNome());
        } else return null;
        log.info("Atualizando disciplina: "+ disciplinaNova );
        return this.repository.save(disciplinaDB);
    }

    public void deletar(@NotNull final Long codigoDisciplina) {

        // Pode Lançar EntityNotFoundException caso nao exista este disciplina
        Disciplina disciplina = this.repository.getOne(codigoDisciplina);

        // Optou-se por esse metodo de delete ao inves de usar o deleteById para
        // tratar manualmente se a entidade existe ou nao no DB antes de remover
        log.info("Deletando disciplina com código "+ codigoDisciplina);

        this.repository.delete(disciplina);
    }

    public List<Disciplina> listarAll() {
        log.info("Listando todas as disciplinas");
        return ImmutableList.copyOf(this.repository.findAll());
    }

    public Optional<Disciplina> buscarPorCodigo(Long codigo) {
        log.info("Buscando disciplina por código: "+ codigo);
        return this.repository.findById(codigo);
    }

    public Optional<Disciplina> buscarPorNome(String nome) {
        log.info("Buscando disciplina pelo nome: "+ nome);
        return this.repository.findByNome(nome);
    }

}
