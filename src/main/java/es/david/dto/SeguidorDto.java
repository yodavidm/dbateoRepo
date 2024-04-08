package es.david.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeguidorDto {
	
    private Long idSeguidor;

    private Long idSeguido;
}
