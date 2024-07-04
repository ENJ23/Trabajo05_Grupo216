package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@Component
@Entity
@Table(name="alumnos")
public class Alumno {
	
	@Id
    @NotBlank
    @Column(name="lu_alumn")
    private String lu;

	@Size(min=8, max=8, message="DNI inválido")
    @NotBlank
    @Column(name="dni_alumn")
    private String dni;

    @NotBlank
    @Column(name="nombre_alumn")
    private String nombre;

    @NotBlank
    @Column(name="apellido_alumn")
    private String apellido;

    @Email
    @Column(name="email_alumn")
    private String email;

    @Pattern(regexp = "\\d{10}")
    @Column(name="tel_alumn")
    private String telefono;

    @Past
    @Column(name="fechNac_alumn")
    private LocalDate fechadenacimiento;

    @NotBlank
    @Column(name="dom_alumn")
    private String domicilio;


    @Column(name="estado_alumn")
    private Boolean estado;
	
	
	@ManyToMany(mappedBy = "alumno") 
	private List<Inscripcion> inscripciones;
	  
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Carr_codigo") 
	private Carrera carrera;
	 
}

