package es.david.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.david.dto.SeguidorDto;
import es.david.entities.Seguidor;
import es.david.entities.Usuario;
import es.david.repositories.SeguidorRepo;

@Service
public class SeguidorService {
	
	private SeguidorRepo seguidorRepo;
	
	public SeguidorService(SeguidorRepo seguidorRepo) {
		this.seguidorRepo = seguidorRepo;
	}
	
	public List<Seguidor> listarSeguidores(){
		return seguidorRepo.findAll();
	}
	
	public Optional<Seguidor> listarSeguidorPorId(Long id){
		Optional<Seguidor> seguidorEncontrado = seguidorRepo.findById(id);
		return seguidorEncontrado;
	}
	
	public Seguidor crearSeguidor(SeguidorDto seguidorNuevo) {
		
		Seguidor seguidor = Seguidor.builder()
				.seguido(Usuario.builder()
						.googleId(seguidorNuevo.getIdSeguido())
						.build())
				.seguidor(Usuario.builder()
						.googleId(seguidorNuevo.getIdSeguidor())
						.build())
				.build();
		
		seguidorRepo.save(seguidor);
		return seguidor;
	}

}
