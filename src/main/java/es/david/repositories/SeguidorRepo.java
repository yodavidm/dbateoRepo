package es.david.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.entities.Seguidor;

@Repository
public interface SeguidorRepo extends JpaRepository<Seguidor, Long> {

}
