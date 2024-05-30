package es.david.services;

import java.sql.Timestamp;
import java.util.Date;
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
import jakarta.transaction.Transactional;


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

	@Transactional
    public Publicacion crearPublicacion(PublicacionDto publicacionDto) {
        // Obtener la fecha actual
        Date fechaActual = new Date();
        Timestamp timestamp = new Timestamp(fechaActual.getTime());  

        // Buscar categoria y usuario
        Optional<Categoria> categoriaOptional = categoriaRepo.findById(publicacionDto.getId_categoria());
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(publicacionDto.getId_usuario());

        if (!categoriaOptional.isPresent()) {
            throw new RuntimeException("Categoria no encontrada");
        }
        if (!usuarioOptional.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Categoria categoria = categoriaOptional.get();
        Usuario usuario = usuarioOptional.get();

        Publicacion publicacion = Publicacion.builder()
                .titulo(publicacionDto.getTitulo())
                .contenido(publicacionDto.getContenido())
                .fecha_creacion(timestamp)
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
	
	public void eliminarPublicacion(Long id) {
		publicacionRepo.deleteById(id);
	}
	
	public List<Publicacion> obtenerPublicacionesPorCategoria(Long idCategoria){
		Optional<Categoria> categoriaOptional = categoriaRepo.findById(idCategoria);
		if(categoriaOptional.isPresent()) {
			Categoria categoria = categoriaOptional.get();
			return publicacionRepo.findByCategoria(categoria);
		}else {
			return List.of();
		}
	}
	
	public List<Publicacion> obtenerPublicacionPorNickname(String nickname){
		return publicacionRepo.findPublicacionesByNickname(nickname);
	}

}
