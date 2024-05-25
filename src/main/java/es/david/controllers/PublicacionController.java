package es.david.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.dto.PublicacionDto;
import es.david.entities.Publicacion;
import es.david.services.PublicacionService;



@RestController
@RequestMapping("/publicaciones")
@CrossOrigin(origins = "*")
public class PublicacionController {
	
	private PublicacionService publicacionService;
	
	
	private PublicacionController(PublicacionService publicacionService) {
		this.publicacionService = publicacionService;
	}
	
	@GetMapping()
	public List<Publicacion> obtenerPublicaciones(){
		return publicacionService.listarPublicaciones();
	}
	
	@PostMapping("/crear")
	public Publicacion crearPublicacion(@RequestBody PublicacionDto publicacionNueva) {
		return publicacionService.crearPublicacion(publicacionNueva);
	}
	
	@GetMapping("/{id}")
	public Optional<Publicacion> obtenerPubliPorId(@PathVariable Long id) {
		Optional<Publicacion> publiEncontrada = publicacionService.buscarPorId(id);
		return publiEncontrada;
	}
	
	@DeleteMapping("/{id}")
	public void eliminarPublicacion(@PathVariable Long id) {
		publicacionService.eliminarPublicacion(id);
	}
	
	@GetMapping("/categoria/{id}")
	public List<Publicacion> obtenerPublicacionPorCategoria(@PathVariable Long id){
		return publicacionService.obtenerPublicacionesPorCategoria(id);
	}


}
