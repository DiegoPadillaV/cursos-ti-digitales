package cl.diego.java.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.diego.java.curso.model.Administrador;
import cl.diego.java.curso.repository.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired
	AdministradorRepository administradorRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	public Administrador crearAdmin(Administrador administrador) {
		String passwordCodificado = encoder.encode(administrador.getPassword());
		administrador.setPassword(passwordCodificado);
		return administradorRepository.saveAndFlush(administrador);
	}
	
	public long contarAdmin() {
		return administradorRepository.count();
	}
}
