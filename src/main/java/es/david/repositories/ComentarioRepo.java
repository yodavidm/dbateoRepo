package es.david.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.entities.Comentario;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Long>{

}
