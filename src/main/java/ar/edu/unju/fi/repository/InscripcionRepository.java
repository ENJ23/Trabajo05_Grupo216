package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.model.Inscripcion;
import ar.edu.unju.fi.model.Materia;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
	
    public List<Inscripcion> findInscripcionByMateria(Materia materia);
}
