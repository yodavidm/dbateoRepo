package es.david.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.entities.Categoria;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Long> {

}
