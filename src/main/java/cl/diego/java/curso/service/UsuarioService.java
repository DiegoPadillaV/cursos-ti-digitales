package cl.diego.java.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.diego.java.curso.model.Estudiante;
import cl.diego.java.curso.repository.EstudianteRepository;

@Service
public class UsuarioService {
	
	@Autowired
	EstudianteRepository estudianteRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	public Estudiante crearUsuario(Estudiante estudiante) {
		String passCodificado = encoder.encode(estudiante.getPassword());
		estudiante.setPassword(passCodificado);
		return estudianteRepository.save(estudiante);
	}
	
	public long contarUsuarios() {
		return estudianteRepository.count();
	}
}
