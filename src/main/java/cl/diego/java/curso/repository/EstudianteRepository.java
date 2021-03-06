package cl.diego.java.curso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.diego.java.curso.model.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
	
	Optional<Estudiante> findByRut(String username);
}
