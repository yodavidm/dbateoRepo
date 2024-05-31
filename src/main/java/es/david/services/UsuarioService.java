package es.david.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.dto.UsuarioDto;
import es.david.entities.Rol;
import es.david.entities.Usuario;
import es.david.repositories.ComentarioRepo;
import es.david.repositories.SeguidorRepo;
import es.david.repositories.UsuarioRepo;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	private UsuarioRepo usuarioRepo;
	
	@Autowired
	private ComentarioRepo comentarioRepo;
	@Autowired
	private SeguidorRepo seguidorRepo;

	@Autowired
	public UsuarioService(UsuarioRepo usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}

	// método para crear usuario
	public Usuario crearUsuario(UsuarioDto usuarioNuevo) {

		Usuario usuario = Usuario.builder().email(usuarioNuevo.getEmail()).nickname(usuarioNuevo.getNickname())
				.fechaRegistro(new Date()).rol(Rol.builder().id(2L).build()).build();

		usuarioRepo.save(usuario);
		return usuario;
	}

	public List<Usuario> listarUsuarios() {
		return usuarioRepo.findAll();
	}

	public Optional<Usuario> findById(Long id) {
		Optional<Usuario> usuarioEncontrado = this.usuarioRepo.findById(id);
		return usuarioEncontrado;
	}

	public Optional<Usuario> getUsuarioByNickname(String nickname) {
		return usuarioRepo.findByNickname(nickname);
	}

	@Transactional
	public void eliminarUsuario(Long id) {
		seguidorRepo.deleteByUsuarioId(id);
		// Eliminar al usuario
		usuarioRepo.deleteById(id);
	}

}
