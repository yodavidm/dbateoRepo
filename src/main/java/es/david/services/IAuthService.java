package es.david.services;

import java.util.HashMap;

import es.david.dto.LoginDTO;
import es.david.dto.ResponseDTO;
import es.david.entities.Usuario;

public interface IAuthService {
	
	public HashMap<String,String> login(LoginDTO login) throws Exception;
	public ResponseDTO register(Usuario usuario) throws Exception;



}
