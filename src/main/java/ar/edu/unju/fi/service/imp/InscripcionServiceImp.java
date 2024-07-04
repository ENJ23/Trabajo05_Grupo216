package ar.edu.unju.fi.service.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.model.Inscripcion;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.InscripcionRepository;
import ar.edu.unju.fi.service.InscripcionService;

@Service
public class InscripcionServiceImp implements InscripcionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InscripcionServiceImp.class);

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public void saveInscripcion(Inscripcion inscripcion) {
        LOGGER.info("Iniciando método saveInscripcion");
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaHoraFormateada = fechaHoraActual.format(formatter);
        inscripcion.setFechaInscripcion(fechaHoraFormateada);
        inscripcionRepository.save(inscripcion);
        LOGGER.info("Inscripción guardada exitosamente");
    }

    @Override
    public List<Inscripcion> showInscripcion() {
        LOGGER.info("Mostrando todas las inscripciones");
        return inscripcionRepository.findAll();
    }

    @Override
    public void eliminarInscripcion(long id) {
        LOGGER.info("Iniciando método eliminarInscripcion con id: " + id);
        List<Inscripcion> todasLasInscripciones = inscripcionRepository.findAll();
        for (Inscripcion i : todasLasInscripciones) {
            if (i.getId().equals(id)) {
                inscripcionRepository.delete(i);
                LOGGER.info("Inscripción eliminada exitosamente");
                break;
            }
        }
    }

    @Override
    public List<Inscripcion> findInscripByMateria(Materia materia) {
        LOGGER.info("Buscando inscripciones por materia");
        List<Inscripcion> inscripciones = inscripcionRepository.findInscripcionByMateria(materia);
        return inscripciones;
    }


}
