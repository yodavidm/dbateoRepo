package es.david.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoDto {

	private boolean tipo;
	private Long id_usuario;
	private Long id_publicacion;

}
