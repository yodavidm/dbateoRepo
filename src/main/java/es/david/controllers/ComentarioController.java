package es.david.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.dto.ComentarioDto;
import es.david.entities.Comentario;
import es.david.services.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
	
	private ComentarioService comentarioService;
	
	private ComentarioController(ComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}
	
	@GetMapping
	public List<Comentario> listarComentarios(){
		return comentarioService.listarComentarios();
	}
	
	@PostMapping
	public ComentarioDto crearComentario(@RequestBody ComentarioDto comentarioNuevo) {
		
		ComentarioDto comentarioDto = ComentarioDto.builder()
				.comentario(comentarioNuevo.getComentario())
				.fecha_creacion(comentarioNuevo.getFecha_creacion())
				.id_usuario(comentarioNuevo.getId_usuario())
				.id_publicacion(comentarioNuevo.getId_publicacion())
				.build();
		
		comentarioService.crearComentario(comentarioDto);
		return comentarioDto;
	}

}
