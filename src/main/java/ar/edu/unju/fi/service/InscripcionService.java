package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Inscripcion;
import ar.edu.unju.fi.model.Materia;

@Service
public interface InscripcionService {
	
	public void saveInscripcion(Inscripcion inscripcion);
	public List<Inscripcion> showInscripcion();
	//public String buscarAlumnoInsc(String lu);
	//public String buscarMatInsc(String codigo);
	public void eliminarInscripcion(long id);
	List<Inscripcion> findInscripByMateria(Materia materia);
}
