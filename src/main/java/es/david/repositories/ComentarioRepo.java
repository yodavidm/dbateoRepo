package es.david.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.david.entities.Comentario;
import jakarta.transaction.Transactional;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Long> {

	List<Comentario> findByPublicacionId(Long idPublicacion);

	@Modifying
	@Transactional
	@Query("DELETE FROM Comentario c WHERE c.usuario.id = :usuarioId")
	void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);

}
