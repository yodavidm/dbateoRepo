package es.david.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.dto.UsuarioDto;
import es.david.entities.Rol;
import es.david.entities.Usuario;
import es.david.repositories.UsuarioRepo;


@Service
public class UsuarioService{
	
	private UsuarioRepo usuarioRepo;

	@Autowired
	public UsuarioService(UsuarioRepo usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}
	
	//método para crear usuario
	public Usuario crearUsuario(UsuarioDto usuarioNuevo){
		
		Usuario usuario = Usuario.builder()
				.email(usuarioNuevo.getEmail())
				.nickname(usuarioNuevo.getNickname())
				.fechaRegistro(usuarioNuevo.getFechaRegistro())
				.rol(Rol.builder()
						.id(2L)
						.build())
				.build();
		
		usuarioRepo.save(usuario);
		return usuario;
	}
	
	public List<Usuario> listarUsuarios(){
		return usuarioRepo.findAll();
	}
	

}
