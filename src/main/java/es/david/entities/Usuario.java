package es.david.entities;

import java.io.Serializable;
import java.sql.Date;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "google_id", nullable = false, unique = true)
	private Long googleId;
	
    @Column(name = "email", nullable = false, unique = true)
	private String email;
    
    @Column(name = "nickname", nullable = false, unique = true)
	private String nickname;
    
    
    @Column(name = "fecha_Registro", nullable = false)
	private Date fechaRegistro;
	
	@ManyToOne
	@JoinColumn(name = "id_rol")
	private Rol rol;
	
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
	private List<Publicacion> publicaciones;
	
	@OneToMany(mappedBy = "seguido",cascade = CascadeType.ALL)
	private List<Seguidor> seguidos;
	
	@OneToMany(mappedBy = "seguidor", cascade = CascadeType.ALL)
	private List<Seguidor> seguidores;
}
