package es.david.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.dto.LoginDTO;
import es.david.dto.ResponseDTO;
import es.david.entities.Usuario;
import es.david.services.IAuthService;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://dbateofront.vercel.app", allowCredentials = "true")
public class AuthControllers {
	
	@Autowired
	IAuthService authService;

	
	@PostMapping("/register")
	private ResponseEntity<ResponseDTO> register(@RequestBody Usuario usuario) throws Exception{
		return new ResponseEntity<>(authService.register(usuario),HttpStatus.CREATED);
	}

	@PostMapping("/login")
	private ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDTO loginRequest) throws Exception{
		HashMap<String, String> login = authService.login(loginRequest);
		if(login.containsKey("jwt")) {
			return new ResponseEntity<>(login,HttpStatus.OK);

		}else {
			return new ResponseEntity<>(login,HttpStatus.UNAUTHORIZED);
		}

	}
}
