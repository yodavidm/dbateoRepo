package es.david.dto;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionDto {

	private String titulo;

	private String contenido;

	private Date fecha_creacion;
	
	private Long id_usuario;
	
	private Long id_categoria;
		
}