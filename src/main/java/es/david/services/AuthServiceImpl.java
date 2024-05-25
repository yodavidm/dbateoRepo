package es.david.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.david.dto.LoginDTO;
import es.david.dto.ResponseDTO;
import es.david.entities.Rol;
import es.david.entities.Usuario;
import es.david.repositories.UsuarioRepo;

@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private IJWTUtilityService jwtUtilityService;

	@Autowired
	private UserValidation userValidation;

	@Override
	public HashMap<String, String> login(LoginDTO login) throws Exception {
		try {
			HashMap<String, String> jwt = new HashMap<>();
			Optional<Usuario> usuario = usuarioRepo.findByEmail(login.getEmail());

			if (usuario.isEmpty()) {
				jwt.put("error", "Usuario no registrado.");
				return jwt;
			}

			// verificar contraseña
			if (verifyPassword(login.getPassword(), usuario.get().getPassword())) {
				jwt.put("jwt", jwtUtilityService.generateJWT(usuario.get().getId()));
			} else {
				jwt.put("error", "Autentificación fallida");
			}
			return jwt;

		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}

	@Override
	public ResponseDTO register(Usuario usuario) throws Exception {
		try {
			ResponseDTO response = userValidation.validate(usuario);
			
			if(response.getNumOfErrors()>0) {
				return response;
			}
			
			List<Usuario> getAllUsers = usuarioRepo.findAll();
			
			for(Usuario usuarioExistente : getAllUsers) {
				if(usuarioExistente.getEmail().equals(usuario.getEmail())) {
					response.setNumOfErrors(1);
					response.setMessage("El email ya existe");
					return response;
				}
			}
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			usuario.setPassword(encoder.encode(usuario.getPassword()));
			usuario.setFechaRegistro(new Date());
			usuario.setRol(Rol.builder().id(1L).build());
			usuarioRepo.save(usuario);
			response.setMessage("Usuario creado con éxito.");
			
			return response;

			
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}

	private boolean verifyPassword(String enteredPassword, String storedPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(enteredPassword, storedPassword);
	}

}
