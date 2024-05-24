package es.david.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.david.entities.Seguidor;

@Repository
public interface SeguidorRepo extends JpaRepository<Seguidor, Long> {
	
    @Query(value = "SELECT * FROM Seguidor WHERE id_seguidor = ?1", nativeQuery = true)
    List<Seguidor> encontrarPorIdUsuarioSeguido(Long idUsuarioSeguidor);
    
    @Query(value = "SELECT * FROM Seguidor WHERE id_seguido = ?1", nativeQuery = true)
    List<Seguidor> encontrarPorIdUsuarioSeguidor(Long idUsuarioSeguido);
    
    boolean existsBySeguidorIdAndSeguidoId(Long seguidorId, Long seguidoId);



}
