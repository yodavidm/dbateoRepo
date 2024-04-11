package es.david.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.dto.CategoriaDto;
import es.david.entities.Categoria;
import es.david.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	private CategoriaService categoriaService;
	
	private CategoriaController (CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@GetMapping
	public List<Categoria> listarCategoria() {
		return categoriaService.listarCategorias();
	}
	
	//se usa dto para traspasar información del dto al objeto original
	@PostMapping
	public Categoria crearCategoria(@RequestBody CategoriaDto categoriaNueva) {

		return categoriaService.crearCategoria(categoriaNueva);
	}

}
