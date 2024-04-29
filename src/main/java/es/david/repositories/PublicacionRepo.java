package es.david.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.entities.Categoria;
import es.david.entities.Publicacion;


@Repository
public interface PublicacionRepo extends JpaRepository<Publicacion, Long> {
	List<Publicacion> findByCategoria(Categoria categoria);

}
