package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

@Service
public interface MateriaService {
	
	public void guardarMateria(Materia materiaParaGuardar);
	public List<Materia> mostrarMateria();
	public void borrarMateria(String codigo);
	public void modificarMateria(MateriaDTO materiaModificada);
	public Materia buscarMateria(String codigo);
	public Materia findMateriaByCodigo(String codigo);
	public Boolean existeMateria(Materia materiaParaGuardar);

}
