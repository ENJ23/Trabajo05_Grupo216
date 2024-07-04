package ar.edu.unju.fi.DTO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import lombok.Data;

@Data
@Component
public class AlumnoDTO {	
	private String lu;
	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private LocalDate fechadenacimiento;
	private String domicilio;
	private Boolean estado;
	private List<Materia> materias;
	private Carrera carrera;
	
}
