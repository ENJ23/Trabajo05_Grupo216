package ar.edu.unju.fi.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import lombok.Data;

@Data
@Component
public class MateriaDTO {

    private String codigo;
    private String nombre;
    private String curso;
    private Integer cantidad;
    private Boolean modalidad;
    private Boolean estado;
    private Carrera carrera;
    private List<Alumno> alumnos;
    private Docente docente;
}