package es.david.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.dto.RolDto;
import es.david.entities.Rol;
import es.david.services.RolService;

@RestController
@RequestMapping("/roles")
public class RolController {

	private RolService rolService;

	private RolController(RolService rolService) {
		this.rolService = rolService;
	}

	@GetMapping
	public List<Rol> obtenerRoles() {
		return rolService.listarRoles();

	}

	@PostMapping
	public Rol crearRol(@RequestBody RolDto rolNuevo) {

		return rolService.crearRol(rolNuevo);
	}

}
