package es.david.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.dto.ComentarioDto;
import es.david.entities.Comentario;
import es.david.entities.Publicacion;
import es.david.services.ComentarioService;
import es.david.services.PublicacionService;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "https://dbateofront.vercel.app", allowCredentials = "true")
public class ComentarioController {
	
	private ComentarioService comentarioService;
	private PublicacionService publicacionService;
	
	private ComentarioController(ComentarioService comentarioService,PublicacionService publicacionService) {
		this.comentarioService = comentarioService;
		this.publicacionService = publicacionService;
	}
	
	@GetMapping
	public List<Comentario> listarComentarios(){
		return comentarioService.listarComentarios();
	}
	
	@PostMapping("/comentar")
	public Comentario crearComentario(@RequestBody ComentarioDto comentarioNuevo) {

		return comentarioService.crearComentario(comentarioNuevo);
	}
	
	@GetMapping("/publicacion/{id}")
	public List<Comentario> obtenerComentariosPorPublicacion(@PathVariable Long id) {
		Optional<Publicacion> publicacionEncontrada = publicacionService.buscarPorId(id);
		if(publicacionEncontrada.isPresent()) {
			Publicacion publicacion = publicacionEncontrada.get();
			return comentarioService.obtenerComentariosPorPublicacion(publicacion);
		}else {
			return List.of();
		}
		
	}
	
	@DeleteMapping("/{id}")
	public void eliminarComentario(@PathVariable Long id) {
		comentarioService.eliminarComentario(id);
	}
}
