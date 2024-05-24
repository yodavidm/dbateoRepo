package es.david.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.entities.Usuario;
import es.david.repositories.ComentarioRepo;
import es.david.services.IUserService;
import es.david.services.UsuarioService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private IUserService userService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ComentarioRepo comentarioRepo;

	@GetMapping("/find-all")
	private ResponseEntity<List<Usuario>> getAllUsers() {
		return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	private Optional<Usuario> findUserById(@PathVariable Long id) {
		Optional<Usuario> usuarioEncontrado = this.usuarioService.findById(id);
		return usuarioEncontrado;
	}

	@GetMapping("/nickname/{nickname}")
	private ResponseEntity<Usuario> getUsuarioByNickname(@PathVariable String nickname) {
		Optional<Usuario> usuarioOpt = usuarioService.getUsuarioByNickname(nickname);
		return usuarioOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/eliminar/{id}")
	private void eliminarUsuario(@PathVariable Long id) {
		try { // eliminar comentarios del usuario
			comentarioRepo.deleteByUsuarioId(id);
			usuarioService.eliminarUsuario(id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
