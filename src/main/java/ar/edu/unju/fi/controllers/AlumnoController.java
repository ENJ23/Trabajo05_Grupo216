package ar.edu.unju.fi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.MateriaService;

@Controller
public class AlumnoController {
	
	@Autowired
	Alumno nuevoAlumno;
	
	@Autowired
	Carrera nuevaCarrera;
	
	@Autowired
	AlumnoService alumnoService;
	
	@Autowired
	MateriaService materiaService;
	
	@Autowired
	CarreraService carreraService;
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		//vista formCarrera.html
		ModelAndView modelView = new ModelAndView("formAlumno");
		//agrega el objeto
		//nuevaCarrera.setNombre("Ingenieria");
		modelView.addObject("nuevoAlumno", nuevoAlumno );
		modelView.addObject("carreras", carreraService.mostrarCarreras());
		modelView.addObject("carrera", nuevaCarrera);
		modelView.addObject("band", false);
		return modelView;
	}
	
	@PostMapping("/guardarAlumno")
	public ModelAndView saveAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoParaGuardar) {
					
		//guardar
		//ListadoCarreras.agregarCarrera(carreraParaGuardar);
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");			
		try {
				//alumnoParaGuardar.setCarrera(alumnoParaGuardar.getCarrera());
				
				alumnoService.guardarAlumno(alumnoParaGuardar);	

			System.out.println("Alumno guardado correctamente");
		}catch(Exception e) {			
			modelView.addObject("errors", true);
			modelView.addObject("cargaAlumnoErrorMessage", "Error al cargar en la BD" + e.getMessage());
			System.out.println(e.getMessage());
		}
		
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());				
		return modelView;		
	}
	
	
	 @GetMapping("/borrarAlumno/{lu}")
	    public ModelAndView deleteAlumnoDelListado(@PathVariable(name = "lu") String lu) {
	        System.out.println("esta es la libreta: " + lu);
	        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
	        try {
	            alumnoService.borrarAlumno(lu);
	        } catch (Exception e) {
	            modelView.addObject("errors", true);
	            modelView.addObject("borrarAlumnoErrorMessage", "Error al borrar en la BD: " + e.getMessage());
	            System.out.println(e.getMessage());
	        }
	        modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
	        return modelView;
	    }

	    @GetMapping("/modificarAlumno/{lu}")
	    public ModelAndView editAlumno(@PathVariable(name = "lu") String lu) {
	        AlumnoDTO alumnoParaModificar = alumnoService.buscarAlumno(lu);
	        ModelAndView modelView = new ModelAndView("formAlumno");
	        modelView.addObject("nuevoAlumno", alumnoParaModificar);
	        modelView.addObject("band", true);
	        return modelView;
	    }

	    @PostMapping("/modificarAlumno")
	    public ModelAndView updateAlumno(@ModelAttribute("nuevoAlumno") AlumnoDTO alumnoModificado) {
	        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
	        try {
	            alumnoService.modificarAlumno(alumnoModificado);
	            alumnoModificado.setEstado(true);
	        } catch (Exception e) {
	            modelView.addObject("errors", true);
	            modelView.addObject("modificarAlumnoErrorMessage", "Error al modificar en la BD: " + e.getMessage());
	            System.out.println(e.getMessage());
	        }
	        modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
	        return modelView;
	    }

	}