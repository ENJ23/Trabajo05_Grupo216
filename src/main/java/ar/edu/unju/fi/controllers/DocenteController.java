package ar.edu.unju.fi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.*;


@Controller
public class DocenteController {
    
    @Autowired
    Docente nuevoDocente;

    @Autowired
    DocenteService docenteService;

    @Autowired
    MateriaService materiaService;

    @GetMapping("/formularioDocente")
    public ModelAndView getFormDocente() {
        ModelAndView modelView = new ModelAndView("formDocente");
        modelView.addObject("nuevoDocente", nuevoDocente);
        modelView.addObject("materias", materiaService.mostrarMateria());
        modelView.addObject("band", false);
        return modelView;
    }

    @PostMapping("/guardarDocente")
    public ModelAndView saveDocente(@ModelAttribute("nuevoDocente") DocenteDTO docenteParaGuardar) {
        ModelAndView modelView = new ModelAndView("listaDeDocentes");
        try {
            docenteService.guardarDocente(docenteParaGuardar);
            modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        } catch (Exception e) {
            modelView.addObject("errors", true);
            modelView.addObject("guardarDocenteErrorMessage", "Error al guardar el docente en la BD: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelView;
    }

    @GetMapping("/eliminarDocente/{legajo}")
    public ModelAndView deleteDocenteDelListado(@PathVariable(name = "legajo") String legajo) {
        ModelAndView modelView = new ModelAndView("listaDeDocentes");
        try {
            docenteService.borrarDocente(legajo);
            modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        } catch (Exception e) {
            modelView.addObject("errors", true);
            modelView.addObject("eliminarDocenteErrorMessage", "Error al eliminar el docente de la BD: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelView;
    }

    @GetMapping("/modificarDocente/{legajo}")
    public ModelAndView editDocente(@PathVariable(name = "legajo") String legajo) {
        ModelAndView modelView = new ModelAndView("formDocente");
        try {
            DocenteDTO docenteParaModificar = docenteService.buscarDocente(legajo);
            modelView.addObject("nuevoDocente", docenteParaModificar);
            modelView.addObject("band", true);
        } catch (Exception e) {
            modelView.addObject("errors", true);
            modelView.addObject("buscarDocenteErrorMessage", "Error al buscar el docente en la BD: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelView;
    }

    @PostMapping("/modificarDocente")
    public ModelAndView updateDocente(@ModelAttribute("nuevoDocente") DocenteDTO docenteModificada) {
        ModelAndView modelView = new ModelAndView("listaDeDocentes");
        try {
            docenteService.modificarDocente(docenteModificada);
            docenteModificada.setEstado(true);
            modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        } catch (Exception e) {
            modelView.addObject("errors", true);
            modelView.addObject("modificarDocenteErrorMessage", "Error al modificar el docente en la BD: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelView;
    }
}
