package ar.edu.unju.fi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.MateriaService;
import ar.edu.unju.fi.DTO.MateriaDTO;


@Controller
public class MateriaController {
    
    @Autowired
    Materia nuevaMateria;

    @Autowired
    MateriaService materiaService;

    @Autowired
    CarreraService carreraService;

    @Autowired
    DocenteService docenteService;

    @GetMapping("/formularioMateria")
    public ModelAndView getFormMateria() {
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", nuevaMateria);
        modelView.addObject("carreras", carreraService.mostrarCarreras());
        modelView.addObject("doc", docenteService.mostrarDocentes());
        modelView.addObject("band", false);
        return modelView;
    }

    @PostMapping("/guardarMateria")
    public ModelAndView guardarMateria(@ModelAttribute("nuevaMateria") Materia materiaParaGuardar) {
    	ModelAndView modelView = new ModelAndView();
        try {
            if (materiaService.existeMateria(materiaParaGuardar)) {
            	modelView.setViewName("formMateria");
                modelView.addObject("errors", true);
                modelView.addObject("cargaMateriaErrorMessage", "La materia ya existe en la base de datos");
            } else {
                materiaService.guardarMateria(materiaParaGuardar);
                System.out.println("Materia guardada correctamente");
            }
        } catch(Exception e) {
            modelView.addObject("errors", true);
            modelView.addObject("cargaMateriaErrorMessage", "Error al cargar en la BD: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        modelView.setViewName("listaDeMaterias");
        modelView.addObject("listadoMaterias", materiaService.mostrarMateria());
        return modelView;
    }
    @GetMapping("/listadoMaterias")
  	public ModelAndView listarMaterias() {
  		
  		ModelAndView modelView = new ModelAndView("listaDeMaterias");
  		modelView.addObject("listadoMaterias", materiaService.mostrarMateria() );
  		return modelView;
  		
  		
  	}

    @GetMapping("/borrarMateria/{codigo}")
    public ModelAndView deleteMateriaDelListado(@PathVariable(name="codigo") String codigo) {
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        try {
            materiaService.borrarMateria(codigo);
            modelView.addObject("listadoMateria", materiaService.mostrarMateria());
        } catch (Exception e) {
            modelView.addObject("errors", true);
            modelView.addObject("borrarMateriaErrorMessage", "Error al borrar la materia de la BD: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelView;
    }

    @GetMapping("/modificarMateria/{codigo}")
    public ModelAndView editMateria(@PathVariable(name="codigo") String codigo) {
        Materia materia = materiaService.buscarMateria(codigo);
        if (materia.getCarrera() != null) materiaService.borrarRelaciones(materia);
    	ModelAndView modelView = new ModelAndView("formMateria");
        try {
            Materia materiaParaModificar = materiaService.buscarMateria(codigo);
            modelView.addObject("nuevaMateria", materiaParaModificar);
            modelView.addObject("carreras", carreraService.mostrarCarreras());
            modelView.addObject("doc", docenteService.mostrarDocentes());
            modelView.addObject("band", true);
        } catch (Exception e) {
            modelView.addObject("errors", true);
            modelView.addObject("buscarMateriaErrorMessage", "Error al buscar la materia en la BD: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelView;
    }

    @PostMapping("/modificarMateria")
    public ModelAndView updateMateria(@ModelAttribute("nuevaMateria") Materia materiaModificada) {
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        
        try {
            materiaService.modificarMateria(materiaModificada);
            modelView.addObject("listadoMaterias", materiaService.mostrarMateria());
        } catch (Exception e) {
            modelView.addObject("errors", true);
            modelView.addObject("modificarMateriaErrorMessage", "Error al modificar la materia en la BD: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        modelView.addObject("listadoMaterias", materiaService.mostrarMateria());
        return modelView;
    }

}
