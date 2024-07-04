package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Materia;
import lombok.Data;

@Data
@Component
public class DocenteDTO {

	 private String legajo;
	 private String nombre;
	 private String apellido;
	 private String email;
	 private String telefono;
	 private boolean estado;
	 private Materia materia;
}