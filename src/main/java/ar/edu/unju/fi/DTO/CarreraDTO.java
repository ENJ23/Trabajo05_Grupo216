package ar.edu.unju.fi.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import lombok.Data;

@Data
@Component
public class CarreraDTO {

	private String codigo;
	private String nombre;
	private Integer duracion;
	private Boolean estado;
	private List<Materia> materias;
	private List<Alumno> alumnos;
}
