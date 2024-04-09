package es.david.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "voto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voto implements Serializable{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tipo_voto")
	private boolean tipo;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuarios;
	
	@ManyToOne
	@JoinColumn(name = "id_publicacion")
	@JsonIgnore
	private Publicacion publicacion;
}
