package es.david.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.entities.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion,Long> {
	
    List<Notificacion> findByUsuarioIdAndLeidaFalse(Long usuarioId);
    
    void deleteByUsuarioId(Long idUsuario);



}
