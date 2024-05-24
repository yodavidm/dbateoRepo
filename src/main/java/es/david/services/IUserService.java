package es.david.services;

import java.util.List;

import es.david.entities.Usuario;

public interface IUserService {

	public List<Usuario> findAllUsers();
}
