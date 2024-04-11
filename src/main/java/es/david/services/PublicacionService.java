package es.david.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.david.dto.PublicacionDto;
import es.david.entities.Categoria;
import es.david.entities.Publicacion;
import es.david.entities.Usuario;
import es.david.repositories.CategoriaRepo;
import es.david.repositories.PublicacionRepo;
import es.david.repositories.UsuarioRepo;


@Service
public class PublicacionService {

	private PublicacionRepo publicacionRepo;
	private CategoriaRepo categoriaRepo;
	private UsuarioRepo usuarioRepo;

	public PublicacionService(PublicacionRepo publicacionRepo,CategoriaRepo categoriaRepo,UsuarioRepo usuarioRepo) {
		this.publicacionRepo = publicacionRepo;
		this.categoriaRepo = categoriaRepo;
		this.usuarioRepo = usuarioRepo;
	}

	public Publicacion crearPublicacion(PublicacionDto publicacionDto) {
		
		Optional<Categoria> categoriaOptional = categoriaRepo.findById(publicacionDto.getId_categoria());
		Optional<Usuario> usuarioOptional = usuarioRepo.findById(publicacionDto.getId_usuario());

		
		Categoria categoria = categoriaOptional.get();	
		Usuario usuario = usuarioOptional.get();
		
	    Publicacion publicacion = Publicacion.builder()
	            .titulo(publicacionDto.getTitulo())
	            .contenido(publicacionDto.getContenido())
	            .fecha_creacion(publicacionDto.getFecha_creacion())
	            .usuario(usuario)
	            .categoria(categoria)
	            .build();

	    // Guardar la publicación en la base de datos
	    publicacionRepo.save(publicacion);
	    return publicacion;
	}


	public List<Publicacion> listarPublicaciones() {
		return publicacionRepo.findAll();
	}
	
	public Optional<Publicacion> buscarPorId(Long id) {
		Optional<Publicacion> publicacionEncontrada = publicacionRepo.findById(id);
		return publicacionEncontrada;
	}

}
