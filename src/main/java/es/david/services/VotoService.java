package es.david.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.david.dto.VotoDto;
import es.david.entities.Publicacion;
import es.david.entities.Usuario;
import es.david.entities.Voto;
import es.david.repositories.UsuarioRepo;
import es.david.repositories.VotoRepo;

@Service
public class VotoService {
	
	private VotoRepo votoRepo;
	private  UsuarioRepo usuarioRepo;
	
	public VotoService(VotoRepo votoRepo, UsuarioRepo usuarioRepo) {
		this.votoRepo = votoRepo;
		this.usuarioRepo=usuarioRepo;
	}
	
	public List<Voto> listarVotos(){
		return votoRepo.findAll();
	}
	
	public Voto crearVoto(VotoDto votoDto) {
		
		Optional<Usuario> usuarioOptional = usuarioRepo.findById(votoDto.getId_usuario());
		Usuario usuario = usuarioOptional.get();
		
		Voto voto = Voto.builder()
				.tipo(votoDto.isTipo())
				.publicacion(Publicacion.builder()
						.id(votoDto.getId_publicacion())
						.build())
				.usuarios(usuario)
				.build();
		
		votoRepo.save(voto);
		return voto;
	}

}
