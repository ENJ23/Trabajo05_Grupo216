package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@Component
@Entity
@Table(name="materias")
public class Materia {
	
	@Id
    @NotBlank
    @Column(name="cod_mat")
    private String codigo;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name="nombre_mat")
    private String nombre;

    @NotBlank
    @Column(name="curso_mat")
    private String curso;

    @NotNull
    @Column(name="cant_mat")
    private Integer cantidad;

    @Column(name="mod_mat")
    private Boolean modalidad;

    @Column(name="estado_mat")
    private Boolean estado;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Carr_cod") 
	private Carrera carrera;
	  
	@ManyToMany(mappedBy = "materia")  
	private List<Inscripcion> Inscripciones;
	  
	@OneToOne
	@JoinColumn(name = "docente_materia", referencedColumnName = "legajo")
	private Docente docentes;
	
}
