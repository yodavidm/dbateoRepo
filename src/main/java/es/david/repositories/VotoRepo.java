package es.david.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.entities.Voto;

@Repository
public interface VotoRepo extends JpaRepository<Voto, Long> {

}
