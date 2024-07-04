package ar.edu.unju.fi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Inscripcion;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.InscripcionService;
import ar.edu.unju.fi.service.MateriaService;

@Controller
public class FiltrarAlumnosPorMateriaController {
	@Autowired
    MateriaService materiaService;

    @Autowired
    AlumnoService alumnoService;
    
    @Autowired
    InscripcionService inscripcionService;

    @Autowired 
    Materia materiaSeleccionada;
    
    @Autowired
    Inscripcion inscripcion;
    
    @GetMapping("/filtrarAlumnosPorMateria")
    public ModelAndView getAlumnFilter() {
		//vista formCarrera.html
		ModelAndView modelView = new ModelAndView("filtrarAlumnosMateria");
		modelView.addObject("materiaSeleccionada", materiaSeleccionada);
		modelView.addObject("materias", materiaService.mostrarMateria());
		return modelView;
	}
    
    @PostMapping("/filtrarPorMateria")
    
    public ModelAndView filtrarAlumnosPorMateria(@RequestParam Materia materia) {
        
    	ModelAndView modelView = new ModelAndView("listaDeAlumnosFiltradosPorMateria");
    	
    	 modelView.addObject("materiaSeleccionada", materia);
         modelView.addObject("alumnosFiltrados", inscripcionService.findInscripByMateria(materia));
        //modelView.addObject("alumnosFiltrados", alumnosFiltrados);
        

        return modelView; 
    }
}
