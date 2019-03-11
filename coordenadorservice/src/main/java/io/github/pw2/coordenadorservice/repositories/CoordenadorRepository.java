package io.github.pw2.coordenadorservice.repositories;

import io.github.pw2.coordenadorservice.models.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {

}
