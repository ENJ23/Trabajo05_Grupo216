package ar.edu.unju.fi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.MateriaService;

@Controller
public class CarreraController {
    
    @Autowired
    Carrera nuevaCarrera;

    @Autowired
    CarreraService carreraService;

    @Autowired
    MateriaService materiaService;

    @Autowired
    DocenteService docenteService;

    @Autowired
    AlumnoService alumnoService;

    @GetMapping("/formularioCarrera")
    public ModelAndView getFormCarrera() {
        ModelAndView modelView = new ModelAndView("formCarrera");
        modelView.addObject("nuevaCarrera", nuevaCarrera);
        modelView.addObject("materias", materiaService.mostrarMateria());
        modelView.addObject("alumnos", alumnoService.mostrarAlumnos());
        modelView.addObject("band", false);
        return modelView;
    }

    @PostMapping("/guardarCarrera")
    public ModelAndView saveCarrera(@ModelAttribute("nuevaCarrera") Carrera carrera) {
        ModelAndView modelView = new ModelAndView("listaDeCarreras");
        try {
            
        	for (Materia m : carrera.getMaterias()) {
                m.setCarrera(carrera);
            }

            for (Alumno a : carrera.getAlumnos()) {
                a.setCarrera(carrera);
            }

            carreraService.guardarCarrera(carrera);
            System.out.println("Carrera guardada correctamente");
        } catch (Exception e) {
            modelView.addObject("errors", true);
            modelView.addObject("cargaCarreraErrorMessage", "Error al cargar en la BD: " + e.getMessage());
            System.out.println(e.getMessage());
        }

        modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
        return modelView;
    }
    
    @GetMapping("/listadoCarreras")
	public ModelAndView listarCarreras() {
		
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras", carreraService.mostrarCarreras() );
		return modelView;
		
		
	}

    @GetMapping("/borrarCarrera/{codigo}")
    public ModelAndView deleteCarreraDelListado(@PathVariable(name="codigo") String codigo) {
        System.out.println("este es el codigo: " + codigo);
        ModelAndView modelView = new ModelAndView("listaDeCarreras");
        try {
            carreraService.borrarCarrera(codigo);
        } catch (Exception e) {
            modelView.addObject("errors", true);
            modelView.addObject("borrarCarreraErrorMessage", "Error al borrar en la BD: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
        return modelView;
    }

    @GetMapping("/modificarCarrera/{codigo}")
    public ModelAndView editCarrera(@PathVariable(name="codigo") String codigo) {
        Carrera carreraParaModificar = carreraService.buscarCarrera(codigo);
        carreraService.borrarRelaciones(carreraParaModificar);
        ModelAndView modelView = new ModelAndView("formCarrera");
        modelView.addObject("nuevaCarrera", carreraParaModificar);
        modelView.addObject("materias", materiaService.mostrarMateria());
        modelView.addObject("alumnos", alumnoService.mostrarAlumnos());
        modelView.addObject("band", true);
        return modelView;
    }

    @PostMapping("/modificarCarrera")
    public ModelAndView updateCarrera(@ModelAttribute("nuevaCarrera") Carrera carreraModificada) {
        ModelAndView modelView = new ModelAndView("listaDeCarreras");
        try {            
        	carreraModificada.getAlumnos().forEach(a -> a.setCarrera(carreraModificada));
			carreraModificada.getMaterias().forEach(m -> m.setCarrera(carreraModificada));
        	carreraModificada.setEstado(true);
            carreraService.modificarCarrera(carreraModificada);

        } catch (Exception e) {
            modelView.addObject("errors", true);
            modelView.addObject("modificarCarreraErrorMessage", "Error al modificar en la BD: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
        return modelView;
    }

}
