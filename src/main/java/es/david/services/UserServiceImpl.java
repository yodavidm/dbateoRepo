package es.david.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.entities.Usuario;
import es.david.repositories.UsuarioRepo;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UsuarioRepo usuarioRepo;

	@Override
	public List<Usuario> findAllUsers() {
		return usuarioRepo.findAll();
	}

	

}
