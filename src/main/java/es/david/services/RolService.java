package es.david.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.dto.RolDto;
import es.david.entities.Rol;
import es.david.repositories.RolRepo;

@Service
public class RolService {

	private RolRepo rolRepo;

	@Autowired
	public RolService(RolRepo rolRepo) {
		this.rolRepo = rolRepo;
	}

	public Rol crearRol(RolDto rolDto) {
		Rol rol = Rol.builder()
				.nombreRol(rolDto.getNombreRol())
				.build();

		rolRepo.save(rol);
		return rol;
	}

	public List<Rol> listarRoles() {
		return rolRepo.findAll();
	}

}
