package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
@Table(name="carreras")
public class Carrera {
	
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotBlank(message="Debe ingresar el código")
	@Id
	@Column(name="Carr_codigo")
	private String codigo;
	
	@NotBlank(message="Debe ingresar el nombre")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "El nombre solo puede contener letras")
	//@Size(min=5, max=40, message="El nombre de la carrera debe tener al menos 5 caracteres")
	@Column(name="Carr_nombre")
	private String nombre;
	
	@NonNull
	//@NotBlank(message="Debe ingresar la duración")
	@Min(value=3, message="indicar carrera mayor o igual a 3 años")
	@Max(value=6, message="Indicar carrera menor o igual a 6 años")
	@Column(name="Carr_duracion")
	private Integer duracion;
	
	@NonNull
	@Column(name="Carr_estado")
	private Boolean estado;
	
	@OneToMany(mappedBy="carrera", cascade=CascadeType.ALL) 
	private List<Materia> materias;
	 
	@OneToMany(mappedBy="carrera", cascade=CascadeType.ALL)
	private List<Alumno> alumnos;
	
	//@OneToOne
	//private Docente docente;
}