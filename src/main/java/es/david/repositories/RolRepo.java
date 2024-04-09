package es.david.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.entities.Rol;

@Repository
public interface RolRepo extends JpaRepository<Rol, Long> {

}
