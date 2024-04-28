package es.david.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "publicacion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publicacion implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publicacion_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "titulo",nullable = false)
	private String titulo;
	
	@Column(name = "contenido",nullable = false)
	private String contenido;
	
	@Column(name = "fecha_creacion",nullable = false)
	private Timestamp fecha_creacion;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	@OneToMany(mappedBy = "publicacion",cascade = CascadeType.ALL)
	private List<Comentario> comentarios;
	
	@OneToMany(mappedBy = "publicacion",cascade = CascadeType.ALL)
	private List<Voto> votos;

}
