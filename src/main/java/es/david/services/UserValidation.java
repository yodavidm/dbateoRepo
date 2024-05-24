package es.david.services;

import es.david.dto.ResponseDTO;
import es.david.entities.Usuario;

public class UserValidation {
	
	public ResponseDTO validate(Usuario usuario) {
		ResponseDTO response = new ResponseDTO();
		
		response.setNumOfErrors(0);
		
		if(usuario.getNickname()==null || usuario.getNickname().length()<5 || usuario.getNickname().length()>15) {
			response.setNumOfErrors(response.getNumOfErrors() + 1);
			response.setMessage("Nickname no puede ser nulo, también debe tener entre 5  y 15 caracteres.");
		}
		if(usuario.getEmail()==null || !usuario.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			response.setNumOfErrors(response.getNumOfErrors() + 1);
			response.setMessage("Email no es válido.");
		}
		if(usuario.getPassword()==null || !usuario.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,16}$")) {
			response.setNumOfErrors(response.getNumOfErrors() + 1);
			response.setMessage("La contraseña debe tener entre 8 y 16 caracteres, al menos un número, una minúscula y una mayuscula.");
		}
		
		return response;
	}

}
