package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Component
@Entity
@Table(name="docentes")
public class Docente {
	@Id
    @NotBlank
    private String legajo;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @Email
    private String email;

    @Pattern(regexp = "\\d{10}")
    private String telefono;

    private boolean estado;
    
    @OneToOne
    private Materia materia;
    
    //@OneToOne
    //private Carrera carrera;
    
	    
}
