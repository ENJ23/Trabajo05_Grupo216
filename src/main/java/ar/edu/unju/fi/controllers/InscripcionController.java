package ar.edu.unju.fi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Inscripcion;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.InscripcionService;
import ar.edu.unju.fi.service.MateriaService;

@Controller
public class InscripcionController {

	@Autowired
	private Inscripcion nuevaInscripcion;

	@Autowired
	private InscripcionService inscripcionService;

	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private MateriaService materiaService;

	@GetMapping("/formularioInscripcion")
	public ModelAndView getFormInscripcion() {

		ModelAndView modelView = new ModelAndView("formInscripcion");

		modelView.addObject("nuevaInscripcion", nuevaInscripcion);
		modelView.addObject("alumnos", alumnoService.mostrarAlumnos());
		modelView.addObject("materias", materiaService.mostrarMateria());
		modelView.addObject("band", false);
		return modelView;
 }

	@PostMapping("/guardarInscripcion")
	public ModelAndView guardarInscripcion(@ModelAttribute("nuevaInscripcion") Inscripcion inscripcion, @RequestParam Alumno alumno, @RequestParam Materia materia) {
		 
		 Alumno alumnoAsociado = alumnoService.findAlumnoByLU(alumno.getLu());
	     Materia materiaAsociada = materiaService.buscarMateria(materia.getCodigo());
		
	     if (alumno != null && materia != null) {
	    	 
	    	 inscripcion.setAlumno(alumnoAsociado);
	         inscripcion.setMateria(materiaAsociada);
	    	 inscripcionService.saveInscripcion(inscripcion);
	     }
		
		// Mostrar Listado
		ModelAndView modelView = new ModelAndView("listaDeInscripciones");
		modelView.addObject("listadoInscripciones", inscripcionService.showInscripcion());

		return modelView;
	}

	@GetMapping("/eliminarInscripcion/{id}")
	public ModelAndView deleteInscripcionDelListado(@PathVariable(name = "id") long id) {
		// borrar
		// ListadoDocentes.eliminarDocente(legajo);
		System.out.println("este es el id: " + id);
		inscripcionService.eliminarInscripcion(id);

		// mostrar el nuevo listado
		ModelAndView modelView = new ModelAndView("listaDeInscripciones");
		modelView.addObject("listadoInscripciones", inscripcionService.showInscripcion());

		return modelView;
	}
	
}
