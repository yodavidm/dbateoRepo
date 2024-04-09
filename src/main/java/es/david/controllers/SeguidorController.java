package es.david.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.dto.SeguidorDto;
import es.david.entities.Seguidor;
import es.david.services.SeguidorService;

@RestController
@RequestMapping("/seguidores")
public class SeguidorController {
	
	private SeguidorService seguidorService;
	
	private SeguidorController(SeguidorService seguidorService) {
		this.seguidorService = seguidorService;
	}
	
	@GetMapping
	public List<Seguidor> listarSeguidores(){
		return seguidorService.listarSeguidores();
	}
	
	@PostMapping
	public SeguidorDto seguir(@RequestBody SeguidorDto seguidorNuevo ) {
		SeguidorDto seguidorDto = SeguidorDto.builder()
				.idSeguido(seguidorNuevo.getIdSeguido())
				.idSeguidor(seguidorNuevo.getIdSeguidor())
				.build();
		
		seguidorService.crearSeguidor(seguidorDto);
		return seguidorDto;
	}
	
	@GetMapping("/{id}")
	public Optional<Seguidor> encontrarPorId(@PathVariable Long id) {
		Optional<Seguidor> seguidorEncontrado = seguidorService.listarSeguidorPorId(id);
		return seguidorEncontrado;
	}

}
