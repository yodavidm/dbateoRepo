package es.david.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.dto.UsuarioDto;
import es.david.entities.Usuario;
import es.david.services.UsuarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping
	public List<Usuario> obtenerUsuarios(){
		return usuarioService.listarUsuarios();
	}
	
	@PostMapping
	public Usuario crearUsuario(@RequestBody UsuarioDto usuarioDto) {
		return usuarioService.crearUsuario(usuarioDto);
	}

}
