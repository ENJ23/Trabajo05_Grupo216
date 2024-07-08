package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
@Service
public interface AlumnoService {
	public void guardarAlumno(Alumno alumnoParaGuardar);
	public List<Alumno> mostrarAlumnos();
	public void borrarAlumno(String lu);
	public Alumno buscarAlumno(String alumno);
	public void modificarAlumno(Alumno alumnoModificado);
	public Alumno findAlumnoByLU(String lu);
	public List<Alumno> findAlumnoByCarrera(String codigo);
	void borrarRelaciones(Alumno alumno);
	
}