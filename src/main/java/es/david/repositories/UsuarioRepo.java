package es.david.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.entities.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

}
