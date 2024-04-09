package es.david.services;

import java.util.List;

import org.springframework.stereotype.Service;

import es.david.dto.ComentarioDto;
import es.david.entities.Comentario;
import es.david.entities.Publicacion;
import es.david.entities.Usuario;
import es.david.repositories.ComentarioRepo;

@Service
public class ComentarioService {
	
	private ComentarioRepo comentarioRepo;
	
	public ComentarioService(ComentarioRepo comentarioRepo) {
		this.comentarioRepo = comentarioRepo;
	}
	
	public List<Comentario> listarComentarios(){
		return comentarioRepo.findAll();
	}
	
	public Comentario crearComentario(ComentarioDto comentarioNuevo) {
		Comentario comentario = Comentario.builder()
				.comentario(comentarioNuevo.getComentario())
				.fecha_creacion(comentarioNuevo.getFecha_creacion())
				.usuario(Usuario.builder()
						.googleId(comentarioNuevo.getId_usuario())
						.build())
				.publicacion(Publicacion.builder()
						.id(comentarioNuevo.getId_publicacion())
						.build())
				.build();
		
		comentarioRepo.save(comentario);
		return comentario;
	}

}
