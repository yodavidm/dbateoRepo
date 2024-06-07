package es.david.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.entities.Notificacion;
import es.david.repositories.NotificacionRepository;
import es.david.services.NotificacionService;

@RestController
@RequestMapping("/notificaciones")
@CrossOrigin("*")
public class NotificacionController {
	
    @Autowired
    private NotificacionRepository notificacionRepository;
    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/usuario/{id}")
    public List<Notificacion> obtenerNotificaciones(@PathVariable Long id) {
        return notificacionRepository.findByUsuarioIdAndLeidaFalse(id);
    }
    
    @DeleteMapping("/usuario/{idUsuario}/eliminar")
    public ResponseEntity<Void> eliminarNotificacionesPorUsuario(@PathVariable Long idUsuario) {
        notificacionService.eliminarNotificacionesPorUsuario(idUsuario);
        return ResponseEntity.ok().build();
    }

}
