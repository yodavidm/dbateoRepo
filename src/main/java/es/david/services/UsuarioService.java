package es.david.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Usuario crearUsuario(Usuario usuario){
		usuarioRepo.save(usuario);
		return usuario;
	}
	
	public List<Usuario> listarUsuarios(){
		return usuarioRepo.findAll();
	}
	

}
