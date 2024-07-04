package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService{

		@Autowired
		AlumnoRepository alumnoRepository;
		@Autowired 
		AlumnoMapDTO alumnoMapDTO;
		
		@Override
		public void guardarAlumno (Alumno alumno) {
			alumno.setEstado(true);
			alumnoRepository.save(alumno);
			
		}

		@Override
		public List<Alumno> mostrarAlumnos() {
			// TODO Auto-generated method stub
			//return alumnoRepository.findAll();
			return alumnoRepository.findAlumnoByEstado(true);
		}

		@Override
		public void borrarAlumno(String lu) {
			System.out.println("esta es la libreta universitaria: "+lu);
			// TODO Auto-generated method stub
			List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
			for (int i = 0; i < todosLosAlumnos.size(); i++) {
			      Alumno alumno = todosLosAlumnos.get(i);
			      if (alumno.getLu().equals(lu)) {
			        alumno.setEstado(false);
			        alumnoRepository.save(alumno);
			        break;
			      }
			    }
		}
		



		@Override
		public AlumnoDTO buscarAlumno(String lu) {
			
			List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
			for (Alumno alumno1 : todosLosAlumnos){
				if (alumno1.getLu().equals(lu)){
					return alumnoMapDTO.convertirAlumnoAAlumnoDTO(alumno1);
				}
			}
			return null;
		}
		
		@Override
		public Alumno findAlumnoByLU (String lu) {
			
			List<Alumno> alumnos = new ArrayList<>();
			
			alumnos = alumnoRepository.findAll();
			for (Alumno a : alumnos) {
				if (a.getLu().equals(lu)) {
					return a;
				}
			}
			return null;
		}
		
		@Override 
		public List<Alumno> findAlumnoByCarrera (String codigo) {
				
			List<Alumno> alumnos = new ArrayList<>();
			List<Alumno> alumnosFiltrados = new ArrayList<>();
			
			alumnos = alumnoRepository.findAll();
			for (Alumno a : alumnos) {
				if (a.getCarrera().getCodigo().equals(codigo)) {
					alumnosFiltrados.add(a);
					return alumnosFiltrados;
				}
			}
			return null;
		}

		@Override
		public void modificarAlumno(AlumnoDTO alumnoModificado) {
			AlumnoDTO alumnoBuscado = buscarAlumno(alumnoModificado.getLu());
		    if (alumnoBuscado != null) {
		    	
			List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
			
				for (int i = 0 ; i < todosLosAlumnos.size() ; i++) {
					
					AlumnoDTO alumno = alumnoMapDTO.convertirAlumnoAAlumnoDTO(todosLosAlumnos.get(i));
					
					if (alumno.getLu().equals(alumnoModificado.getLu())) {
						todosLosAlumnos.set(i, alumnoMapDTO.convertirAlumnoDTOAAlumno(alumnoModificado));
						break;
						}
					}
				alumnoRepository.saveAll(todosLosAlumnos);
			   } 
		    	else {
		    		System.out.println("El docente no se ha encontrado ");
		    }
		}


}
