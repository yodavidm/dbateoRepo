package es.david.services;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.entities.Comentario;
import es.david.entities.Notificacion;
import es.david.entities.Seguidor;
import es.david.entities.Usuario;
import es.david.repositories.NotificacionRepository;
import jakarta.transaction.Transactional;

@Service
public class NotificacionService {
	
    @Autowired
    private NotificacionRepository notificacionRepository;

    public void crearNotificacion(Usuario usuario, String mensaje) {
        Notificacion notificacion = Notificacion.builder()
            .usuario(usuario)
            .mensaje(mensaje)
            .fechaCreacion(Timestamp.from(Instant.now()))
            .build();
        notificacionRepository.save(notificacion);
    }
    
    public void notificarComentario(Comentario comentario) {
        Usuario autorPublicacion = comentario.getPublicacion().getUsuario();
        String mensaje = comentario.getUsuario().getNickname() + " comentó tu publicación con título: " + comentario.getPublicacion().getTitulo() + " el siguiente comentario: " + comentario.getComentario();
        crearNotificacion(autorPublicacion, mensaje);
    }
    
    public void notificarSeguimiento(Seguidor seguidor) {
    	Usuario usuarioSeguido = seguidor.getSeguido();
    	String mensaje = seguidor.getSeguidor().getNickname() + " comenzó a seguirte";
    	crearNotificacion(usuarioSeguido, mensaje);
    }
    
    @Transactional
    public void eliminarNotificacionesPorUsuario(Long idUsuario) {
        // Lógica para eliminar las notificaciones del usuario con el ID proporcionado
        notificacionRepository.deleteByUsuarioId(idUsuario);
    }
    

}
