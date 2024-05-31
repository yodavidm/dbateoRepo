package es.david.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.david.entities.Seguidor;
import jakarta.transaction.Transactional;

@Repository
public interface SeguidorRepo extends JpaRepository<Seguidor, Long> {
	
    @Query(value = "SELECT * FROM seguidor WHERE id_seguidor = ?1", nativeQuery = true)
    List<Seguidor> encontrarPorIdUsuarioSeguido(Long idUsuarioSeguidor);
    
    @Query(value = "SELECT * FROM seguidor WHERE id_seguido = ?1", nativeQuery = true)
    List<Seguidor> encontrarPorIdUsuarioSeguidor(Long idUsuarioSeguido);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM seguidor WHERE id_seguido = ?1 AND id_seguidor = ?2", nativeQuery = true)
    void eliminarSeguimiento(Long idSeguido, Long idSeguidor);
    
    boolean existsBySeguidorIdAndSeguidoId(Long seguidorId, Long seguidoId);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM seguidor WHERE id_seguidor = ?1 OR id_seguido = ?1", nativeQuery = true)
    void deleteByUsuarioId(Long usuarioId);
    



}
