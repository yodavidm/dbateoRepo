package es.david.services;

import java.util.List;

import org.springframework.stereotype.Service;

import es.david.dto.PublicacionDto;
import es.david.entities.Publicacion;
import es.david.entities.Usuario;
import es.david.repositories.PublicacionRepo;


@Service
public class PublicacionService {

	private PublicacionRepo publicacionRepo;

	private PublicacionService(PublicacionRepo publicacionRepo) {
		this.publicacionRepo = publicacionRepo;
	}

	public Publicacion crearPublicacion(PublicacionDto publicacionDto) {
		Publicacion publicacion = Publicacion.builder()
				.titulo(publicacionDto.getTitulo())
				.contenido(publicacionDto.getContenido())
				.fecha_creacion(publicacionDto.getFecha_creacion())
				.usuario(Usuario.builder()
						.googleId(publicacionDto.getId_usuario())
						.build())
				.build();
		
		publicacionRepo.save(publicacion);
		return publicacion;
	}

	public List<Publicacion> listarPublicaciones() {
		return publicacionRepo.findAll();
	}

}
