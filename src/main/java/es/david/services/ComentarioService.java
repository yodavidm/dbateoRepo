package es.david.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.dto.ComentarioDto;
import es.david.entities.Categoria;
import es.david.entities.Comentario;
import es.david.entities.Publicacion;
import es.david.entities.Usuario;
import es.david.repositories.ComentarioRepo;
import es.david.repositories.PublicacionRepo;
import es.david.repositories.UsuarioRepo;

@Service
public class ComentarioService {

	private ComentarioRepo comentarioRepo;
	private UsuarioRepo usuarioRepo;
	private PublicacionRepo publicacionRepo;
	private NotificacionService notificacionService;

	public ComentarioService(ComentarioRepo comentarioRepo, UsuarioRepo usuarioRepo, PublicacionRepo publicacionRepo,
			NotificacionService notificacionService) {
		this.comentarioRepo = comentarioRepo;
		this.usuarioRepo = usuarioRepo;
		this.publicacionRepo = publicacionRepo;
		this.notificacionService = notificacionService;
	}

	public List<Comentario> listarComentarios() {
		return comentarioRepo.findAll();
	}

	public Comentario crearComentario(ComentarioDto comentarioNuevo) {

		Optional<Usuario> usuarioOptional = usuarioRepo.findById(comentarioNuevo.getId_usuario());
		Usuario usuario = usuarioOptional.get();
		Optional<Publicacion> publicacionOptional = publicacionRepo.findById(comentarioNuevo.getId_publicacion());
		Publicacion publicacion = publicacionOptional.get();

		Date fechaActual = new Date();
		Timestamp timestamp = new Timestamp(fechaActual.getTime());

		Comentario comentario = Comentario.builder().comentario(comentarioNuevo.getComentario())
				.fecha_creacion(timestamp).usuario(usuario).publicacion(publicacion).build();

		comentarioRepo.save(comentario);

		notificacionService.notificarComentario(comentario);

		return comentario;
	} // devolver dto en un futuro

	// obtener comentarios de una publicación por id
	public List<Comentario> obtenerComentariosPorPublicacion(Publicacion publicacion) {
		return this.comentarioRepo.findByPublicacionId(publicacion.getId());
	}

	public void eliminarComentario(Long id) {
		try {
			comentarioRepo.deleteById(id);
		} catch (Exception e) {
			System.out.println("Hubo un error al eliminar");
		}
	}

}
