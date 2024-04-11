package es.david.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.david.dto.ComentarioDto;
import es.david.entities.Categoria;
import es.david.entities.Comentario;
import es.david.entities.Publicacion;
import es.david.entities.Usuario;
import es.david.repositories.ComentarioRepo;
import es.david.repositories.UsuarioRepo;

@Service
public class ComentarioService {
	
	private ComentarioRepo comentarioRepo;
	private UsuarioRepo usuarioRepo;
	
	public ComentarioService(ComentarioRepo comentarioRepo,UsuarioRepo usuarioRepo) {
		this.comentarioRepo = comentarioRepo;
		this.usuarioRepo=usuarioRepo;
	}
	
	public List<Comentario> listarComentarios(){
		return comentarioRepo.findAll();
	}
	
	public Comentario crearComentario(ComentarioDto comentarioNuevo) {
		
		Optional<Usuario> usuarioOptional = usuarioRepo.findById(comentarioNuevo.getId_usuario());
		Usuario usuario = usuarioOptional.get();
		
		Comentario comentario = Comentario.builder()
				.comentario(comentarioNuevo.getComentario())
				.fecha_creacion(comentarioNuevo.getFecha_creacion())
				.usuario(usuario)
				.publicacion(Publicacion.builder()
						.id(comentarioNuevo.getId_publicacion())
						.build())
				.build();
		
		comentarioRepo.save(comentario);
		return comentario;
	}

}
