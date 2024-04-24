package es.david.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.dto.UsuarioDto;
import es.david.entities.Usuario;
import es.david.services.UsuarioService;

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
	
	@PostMapping("/crear")
	public Usuario crearUsuario(@RequestBody UsuarioDto usuarioDto) {
		System.out.println(usuarioDto.toString());
		return usuarioService.crearUsuario(usuarioDto);
	}

}
