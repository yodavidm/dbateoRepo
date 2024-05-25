package es.david.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin(origins = "https://dbateofront.vercel.app", allowCredentials = "true")
public class SeguidorController {
	
	private SeguidorService seguidorService;
	
	private SeguidorController(SeguidorService seguidorService) {
		this.seguidorService = seguidorService;
	}
	
	@GetMapping
	public List<Seguidor> listarSeguidores(){
		return seguidorService.listarSeguidores();
	}
	
	@PostMapping("/seguir")
	public Seguidor seguir(@RequestBody SeguidorDto seguidorNuevo ) throws Exception {
		return seguidorService.crearSeguidor(seguidorNuevo);
	}
	
	@GetMapping("/{id}/seguidos")
	public List<Seguidor> obtenerSeguidos(@PathVariable Long id) {
	    return seguidorService.obtenerSeguidosPorUsuario(id);
	}
	
	@GetMapping("/{id}/seguidores")
	public List<Seguidor> obtenerSeguidores(@PathVariable Long id) {
	    return seguidorService.obtenerSeguidoresPorUsuario(id);
	}

    @GetMapping("/verificar/{idSeguidor}/{idSeguido}")
    public boolean verificarSeguimientoExistente(@PathVariable Long idSeguidor, @PathVariable Long idSeguido) {
        return seguidorService.verificarSeguimientoExistente(idSeguidor, idSeguido);
    }
    
}
