package io.github.pw2.ambienteservice.services;

import com.google.common.collect.ImmutableList;
import io.github.pw2.ambienteservice.models.Ambiente;
import io.github.pw2.ambienteservice.repositories.AmbienteRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class AmbienteService {

    private final AmbienteRepository repository;

    public AmbienteService(AmbienteRepository repository) {
        this.repository = repository;
    }

    public Ambiente salvar(Ambiente ambiente) {
        return this.repository.save(ambiente);
    }

    public Ambiente atualizar(@NotNull Ambiente ambienteNovo, @NotNull String codigoAmbiente) {

        // Lança EntityNotFoundException caso nao exista este ambiente
        Ambiente ambienteDB = this.repository.getOne(codigoAmbiente);

        ambienteDB.setNome(ambienteNovo.getNome());

        if (ambienteNovo.getCodigo() != null && !ambienteNovo.getCodigo().isEmpty())
            ambienteDB.setCodigo(ambienteNovo.getCodigo());

        return this.repository.save(ambienteDB);
    }

    public void remover(@NotNull String codigoAmbiente) {

        // Lança EntityNotFoundException caso nao exista este ambiente
        Ambiente ambiente = this.repository.getOne(codigoAmbiente);

        // Optou-se por esse metodo de delete ao inves de usar o deleteById para
        // tratar manualmente se a entidade existe ou nao no DB antes de remover
        this.repository.delete(ambiente);
    }

    public List<Ambiente> listarTodos() {
        return ImmutableList.copyOf(this.repository.findAll());
    }

    public Optional<Ambiente> recuperarPorCodigo(String codigo) {
        return this.repository.findById(codigo);
    }

    public Optional<Ambiente> recuperarPorNome(String nome) {
        return this.repository.findByNome(nome);
    }

}
