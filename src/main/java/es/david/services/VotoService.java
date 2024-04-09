package es.david.services;

import java.util.List;

import org.springframework.stereotype.Service;

import es.david.dto.VotoDto;
import es.david.entities.Publicacion;
import es.david.entities.Usuario;
import es.david.entities.Voto;
import es.david.repositories.VotoRepo;

@Service
public class VotoService {
	
	private VotoRepo votoRepo;
	
	public VotoService(VotoRepo votoRepo) {
		this.votoRepo = votoRepo;
	}
	
	public List<Voto> listarVotos(){
		return votoRepo.findAll();
	}
	
	public Voto crearVoto(VotoDto votoDto) {
		Voto voto = Voto.builder()
				.tipo(votoDto.isTipo())
				.publicacion(Publicacion.builder()
						.id(votoDto.getId_publicacion())
						.build())
				.usuarios(Usuario.builder()
						.googleId(votoDto.getId_usuario())
						.build())
				.build();
		
		votoRepo.save(voto);
		return voto;
	}

}
