package es.david.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.dto.CategoriaDto;
import es.david.entities.Categoria;
import es.david.repositories.CategoriaRepo;

@Service
public class CategoriaService {
	
	private CategoriaRepo categoriaRepo;
	
	@Autowired
	private CategoriaService(CategoriaRepo categoriaRepo) {
		this.categoriaRepo = categoriaRepo;
	}
	
	//se encarga de mapeaar el dto y persistir en bd
	public Categoria crearCategoria(CategoriaDto categoriaDto) {
		Categoria categoria = Categoria.builder()
				.nombre(categoriaDto.getNombre())
				.build();
		
		categoriaRepo.save(categoria);
		return categoria;
	}
	
	public List<Categoria> listarCategorias(){
		return categoriaRepo.findAll();
	}

}
