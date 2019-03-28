package io.github.pw2.cursoservice.services;

import com.google.common.collect.ImmutableList;
import io.github.pw2.cursoservice.models.Curso;
import io.github.pw2.cursoservice.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public Curso salvar(Curso curso) {

        curso.getDisciplinas().forEach(disciplina -> disciplina.setCurso(curso));

        return this.repository.save(curso);
    }

    public Curso atualizar(@NotNull final Curso cursoNovo, @NotNull final Long codigoCurso) {

        // Pode Lançar EntityNotFoundException caso nao exista este curso
        Curso cursoDB = this.repository.getOne(codigoCurso);

        if (cursoNovo.getNome() != null && !cursoNovo.getNome().isEmpty())
            cursoDB.setNome(cursoNovo.getNome());

        if (cursoNovo.getDescricao() != null && !cursoNovo.getDescricao().isEmpty())
            cursoDB.setDescricao(cursoNovo.getDescricao());

        if (cursoNovo.getDisciplinas() != null && !cursoNovo.getDisciplinas().isEmpty()) {

            cursoDB.getDisciplinas().clear();
            cursoDB.getDisciplinas().addAll(cursoNovo.getDisciplinas());
            cursoDB.getDisciplinas().forEach(disciplina -> disciplina.setCurso(cursoDB));
        }

        return this.repository.save(cursoDB);
    }

    public void deletar(@NotNull final Long codigoCurso) {

        // Pode Lançar EntityNotFoundException caso nao exista este curso
        Curso curso = this.repository.getOne(codigoCurso);

        // Optou-se por esse metodo de delete ao inves de usar o deleteById para
        // tratar manualmente se a entidade existe ou nao no DB antes de remover
        this.repository.delete(curso);
    }

    public List<Curso> listarAll() {
        return ImmutableList.copyOf(this.repository.findAll());
    }

    public Optional<Curso> buscarPorCodigo(Long codigo) {
        return this.repository.findById(codigo);
    }

    public Optional<Curso> buscarPorNome(String nome) {
        return this.repository.findByNome(nome);
    }

}
