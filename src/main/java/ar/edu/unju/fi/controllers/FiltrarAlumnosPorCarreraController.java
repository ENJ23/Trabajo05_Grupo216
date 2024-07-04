package ar.edu.unju.fi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.MateriaService;

@Controller
public class FiltrarAlumnosPorCarreraController {

	
	@Autowired
    MateriaService materiaService;

    @Autowired
    AlumnoService alumnoService;
    
    @Autowired
    CarreraService carreraService;

    @Autowired 
    Carrera carreraConsulta;
    
    
    
    @GetMapping("/filtrarAlumnosPorCarrera")
    public ModelAndView getAlumnFilter() {
		//vista formCarrera.html
		ModelAndView modelView = new ModelAndView("filtrarAlumnosCarrera");
		modelView.addObject("carrera", carreraConsulta);
		modelView.addObject("carreras", carreraService.mostrarCarreras());
		return modelView;
	}
    
    @PostMapping("/filtrarPorCarrera")
    //public ModelAndView filtrarPorCarrera(@ModelAttribute("carrera") Carrera carreraConsulta) {
      public ModelAndView filtrarPorCarrera(@RequestParam Carrera carrera) {
    	ModelAndView modelView = new ModelAndView("listaDeAlumnosFiltradosPorCarrera");
		modelView.addObject("carreraSeleccionada", carrera);
		modelView.addObject("alumnosFiltrados", alumnoService.findAlumnoByCarrera(carrera.getCodigo()));
        return modelView; 
    }
}
