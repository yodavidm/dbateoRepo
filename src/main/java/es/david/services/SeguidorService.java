package es.david.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.david.dto.SeguidorDto;
import es.david.entities.Seguidor;
import es.david.entities.Usuario;
import es.david.repositories.SeguidorRepo;
import es.david.repositories.UsuarioRepo;

@Service
public class SeguidorService {
	
	private SeguidorRepo seguidorRepo;
	private UsuarioRepo usuarioRepo;
	
	public SeguidorService(SeguidorRepo seguidorRepo,UsuarioRepo usuarioRepo) {
		this.seguidorRepo = seguidorRepo;
		this.usuarioRepo = usuarioRepo;
	}
	
	public List<Seguidor> listarSeguidores(){
		return seguidorRepo.findAll();
	}
	
	public Optional<Seguidor> listarSeguidorPorId(Long id){
		Optional<Seguidor> seguidorEncontrado = seguidorRepo.findById(id);
		return seguidorEncontrado;
	}
	
	public Seguidor crearSeguidor(SeguidorDto seguidorNuevo) {
		
		Optional<Usuario> usuarioOptSeguido = usuarioRepo.findById(seguidorNuevo.getIdSeguido());
		Usuario usuarioSeguido = usuarioOptSeguido.get();
		
		Optional<Usuario> usuarioOptSeguidor = usuarioRepo.findById(seguidorNuevo.getIdSeguidor());
		Usuario usuarioSeguidor = usuarioOptSeguidor.get();
		
		Seguidor seguidor = Seguidor.builder()
				.seguido(usuarioSeguido)
				.seguidor(usuarioSeguidor)
				.build();
		
		seguidorRepo.save(seguidor);
		return seguidor;
	}

}
