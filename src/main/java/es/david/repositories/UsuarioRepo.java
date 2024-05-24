package es.david.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.david.entities.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
	
	@Query(value = "SELECT * FROM usuario WHERE email = :email",nativeQuery = true)
	Optional<Usuario> findByEmail(String email);
	
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    
    @Query(value = "SELECT * FROM usuario WHERE nickname = :nickname", nativeQuery = true)
    Optional<Usuario> findByNickname(String nickname);
}
