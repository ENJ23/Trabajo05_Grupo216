package ar.edu.unju.fi.service.imp;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;

@Service
public class DocenteServiceImp implements DocenteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocenteServiceImp.class);

    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    DocenteMapDTO docenteMapDTO;

    @Override
    public void guardarDocente(DocenteDTO docente) {
        LOGGER.info("Iniciando método guardarDocente");
        docenteMapDTO.convertirDocenteDTOADocente(docente);
        docenteRepository.save(docenteMapDTO.convertirDocenteDTOADocente(docente));
        LOGGER.info("Docente guardado exitosamente");
    }

    @Override
    public List<Docente> mostrarDocentes() {
        LOGGER.info("Mostrando todos los docentes activos");
        return docenteRepository.findDocenteByEstado(true);
    }

    @Override
    public void borrarDocente(String legajo) {
        LOGGER.info("Iniciando método borrarDocente con legajo: " + legajo);
        List<Docente> todosLosDocentes = docenteRepository.findAll();
        for (int i = 0; i < todosLosDocentes.size(); i++) {
            Docente docente = todosLosDocentes.get(i);
            if (docente.getLegajo().equals(legajo)) {
                docente.setEstado(false);
                docenteRepository.save(docente);
                LOGGER.info("Docente borrado exitosamente");
                break;
            }
        }
    }

    @Override
    public DocenteDTO buscarDocente(String legajo) {
        LOGGER.info("Buscando docente con legajo: " + legajo);
        List<Docente> todasLasDocentes = docenteRepository.findAll();
        for (Docente docente : todasLasDocentes) {
            if (docente.getLegajo().equals(legajo)) {
                LOGGER.info("Docente encontrado");
                return docenteMapDTO.convertirDocenteADocenteDTO(docente);
            }
        }
        LOGGER.warn("Docente no encontrado");
        return null;
    }

    @Override
    public void modificarDocente(DocenteDTO docenteModificada) {
        LOGGER.info("Iniciando método modificarDocente");
        DocenteDTO docenteBuscada = buscarDocente(docenteModificada.getLegajo());
        if (docenteBuscada != null) {
            List<Docente> todasLasDocentes = docenteRepository.findAll();
            for (int i = 0; i < todasLasDocentes.size(); i++) {
                DocenteDTO docente = docenteMapDTO.convertirDocenteADocenteDTO(todasLasDocentes.get(i));
                if (docente.getLegajo().equals(docenteModificada.getLegajo())) {
                    todasLasDocentes.set(i, docenteMapDTO.convertirDocenteDTOADocente(docenteModificada));
                    LOGGER.info("Docente modificado exitosamente");
                    break;
                }
            }
            docenteRepository.saveAll(todasLasDocentes);
        } else {
            LOGGER.warn("El docente no se ha encontrado");
        }
    }

    @Override
    public Docente GetDocenteByLegajo(String legajo) {
        LOGGER.info("Obteniendo docente por legajo: " + legajo);
        List<Docente> docentes = docenteRepository.findAll();
        for (Docente d : docentes) {
            if (d.getLegajo().equals(legajo)) {
                LOGGER.info("Docente encontrado por legajo");
                return d;
            }
        }
        LOGGER.warn("Docente no encontrado por legajo");
        return null;
    }

}
