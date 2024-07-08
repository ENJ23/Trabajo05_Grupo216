package ar.edu.unju.fi.service.imp;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.map.MateriaMAPDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;

@Service
public class MateriaServiceImp implements MateriaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MateriaServiceImp.class);

    @Autowired
    MateriaRepository materiaRepository;

    @Autowired
    MateriaMAPDTO materiaMapDTO;

    @Override
    public void guardarMateria(Materia materia) {
        LOGGER.info("Iniciando método guardarMateria");
        materia.setEstado(true);
        materiaRepository.save(materia);
        LOGGER.info("Materia guardada exitosamente");
    }

    @Override
    public List<Materia> mostrarMateria() {
        LOGGER.info("Mostrando todas las materias activas");
        return materiaRepository.findMateriaByEstado(true);
    }

    @Override
    public void borrarMateria(String codigo) {
        LOGGER.info("Iniciando método borrarMateria con código: " + codigo);
        List<Materia> materias = materiaRepository.findAll();
		materias.forEach(materia -> {
			if(materia.getCodigo().equals(codigo)) {
				materia.setDocentes(null);
				materia.setEstado(false);
				materiaRepository.save(materia); 
				LOGGER.info("Materia eliminada exitosamente");
			}
		});
               
            }

    @Override
    public void modificarMateria(Materia materiaModificada) {
        LOGGER.info("Iniciando método modificarMateria");
        Materia materiaBuscada = buscarMateria(materiaModificada.getCodigo());
        
        if (materiaBuscada != null) {
        	materiaModificada.setEstado(true);
    		materiaRepository.save(materiaModificada);
            
            LOGGER.info("Materia modificada exitosamente");
        } else {
            LOGGER.warn("La materia no se ha encontrado");
        }
    }

    @Override
    public Materia buscarMateria(String codigo) {
        LOGGER.info("Buscando materia con código: " + codigo);
        List<Materia> todasLasMaterias = materiaRepository.findAll();
        for (Materia materia : todasLasMaterias) {
            if (materia.getCodigo().equals(codigo)) {
                LOGGER.info("Materia encontrada");
                return materia;
            }
        }
        LOGGER.warn("Materia no encontrada");
        return null;
    }

    @Override
    public Materia findMateriaByCodigo(String codigo) {
        LOGGER.info("Buscando materia por código: " + codigo);
        List<Materia> materias = materiaRepository.findAll();
        for (Materia m : materias) {
            if (m.getCodigo().equals(codigo)) {
                return m;
            }
        }
        LOGGER.warn("Materia no encontrada por código");
        return null;
    }

    @Override
    public Boolean existeMateria(Materia materia) {
        LOGGER.info("Verificando si la materia ya existe");
        Materia existingMateria = materiaRepository.findMateriaByCodigo(materia.getCodigo());
        return existingMateria != null;
    }

	@Override
	public void borrarRelaciones(Materia materia) {
		// TODO Auto-generated method stub
		materia.setCarrera(null);
		materiaRepository.save(materia);
	}

}