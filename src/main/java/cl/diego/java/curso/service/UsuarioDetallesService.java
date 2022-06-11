package cl.diego.java.curso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.diego.java.curso.model.Administrador;
import cl.diego.java.curso.model.Estudiante;
import cl.diego.java.curso.repository.AdministradorRepository;
import cl.diego.java.curso.repository.EstudianteRepository;
import cl.diego.java.curso.security.UsuarioSeguridad;

@Service
public class UsuarioDetallesService implements UserDetailsService {
	

	@Autowired
	EstudianteRepository estudianteRepository;
	
	@Autowired
	AdministradorRepository administradorRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Estudiante> usuarioOpt = estudianteRepository.findByRut(username);
		if(usuarioOpt.isPresent()) {
			return new UsuarioSeguridad(usuarioOpt.get(), null);
		}else {
			Optional<Administrador> adminOpt = administradorRepository.findByUsername(username);
			if(adminOpt.isPresent()) {
				return new UsuarioSeguridad(null, adminOpt.get());
			}
		}
		throw new UsernameNotFoundException("Usuario no encontrado");
	}
}
