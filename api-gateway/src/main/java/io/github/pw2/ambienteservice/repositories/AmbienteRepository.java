package io.github.pw2.ambienteservice.repositories;

import io.github.pw2.ambienteservice.models.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmbienteRepository extends JpaRepository<Ambiente, String> {

    public Optional<Ambiente> findByNome(String nome);

}
