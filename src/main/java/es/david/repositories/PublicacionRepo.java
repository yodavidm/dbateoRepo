package es.david.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.entities.Publicacion;


@Repository
public interface PublicacionRepo extends JpaRepository<Publicacion, Long> {

}
