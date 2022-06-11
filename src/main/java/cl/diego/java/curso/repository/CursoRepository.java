package cl.diego.java.curso.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.diego.java.curso.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	void deleteById(Long id);
}
