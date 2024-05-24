package es.david.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.repositories.UsuarioRepo;

@RestController
@RequestMapping("/validar")
@CrossOrigin(origins = "*")
public class ValidationController {
	
    @Autowired
    private UsuarioRepo usuarioRepository;

    @PostMapping()
    public Map<String, Boolean> validarUsuario(@RequestBody Map<String, String> requestBody) {
        String nickname = requestBody.get("nickname");
        String email = requestBody.get("email");

        boolean nicknameExists = usuarioRepository.existsByNickname(nickname);
        boolean emailExists = usuarioRepository.existsByEmail(email);

        Map<String, Boolean> response = new HashMap<>();
        response.put("nicknameExists", nicknameExists);
        response.put("emailExists", emailExists);
        return response;
    }

}
