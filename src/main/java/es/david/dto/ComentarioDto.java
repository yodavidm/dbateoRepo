package es.david.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDto {

	private String comentario;
	private Timestamp fecha_creacion;
	
	private Long id_usuario;
	private Long id_publicacion;


}
