package cl.diego.java.curso;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cl.diego.java.curso.model.Administrador;
import cl.diego.java.curso.model.Curso;
import cl.diego.java.curso.model.Estudiante;
import cl.diego.java.curso.repository.CursoRepository;
import cl.diego.java.curso.service.AdministradorService;
import cl.diego.java.curso.service.UsuarioService;


@SpringBootApplication
public class CursoTiDigitalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoTiDigitalesApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner datosIniciales(AdministradorService aService, UsuarioService uService, CursoRepository cRepo) {
		return args -> {
			if(aService.contarAdmin() == 0) {
				Administrador admin = Administrador.builder()
													.username("admin")
													.password("1234")
													.build();
				aService.crearAdmin(admin);
														
			}
			
			if(uService.contarUsuarios() == 0) {
				Estudiante estudiante = Estudiante.builder()
											.nombre("Diego")
											.direccion("El Picadero 47")
											.region("IX - Región de la Araucanía")
											.telefono("985942571")
											.email("d.padillavillarrubia@gmail.com")
											.rut("18437142-1")
											.password("334466")
											.comuna("Temuco")
											.build()
									;
				uService.crearUsuario(estudiante);
			}
			if(cRepo.count() == 0) {
				Curso cursoJava = Curso.builder()
										.nombre("Fundamentos de Programacion en Java")
										.fechaInicio(LocalDate.of(2023, 6, 30))
										.fechaFin(LocalDate.of(2024, 1, 1))
										.cupos(30)
										.descripcion("Curso de fundamentos de programación con Java. Aprende la programación orientada a Objetos con Java y Eclipse utilizando la última versión de Java!")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/java.jpg")))
										.build();
				Curso cursoSpring = Curso.builder()
										.nombre("Desarrollo Web con HTML5, CSS y Bootstrap")
										.fechaInicio(LocalDate.of(2023, 6, 30))
										.fechaFin(LocalDate.of(2024, 2, 1))
										.cupos(30)
										.descripcion("Desarrollo web con HTML5, CSS y la incorporacion de Bootstrap, enfocado en el diseño de paginas web utilizando las ultimas tecnologias en la industria.")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/htmlcss.jpeg")))
										.build();
				Curso cursoOracle = Curso.builder()
										.nombre("Bases de Datos Relacionales con Oracle")
										.fechaInicio(LocalDate.of(2023, 6, 20))
										.fechaFin(LocalDate.of(2024, 3, 10))
										.cupos(30)
										.descripcion("Si tiene interés en dominar la administración de una base de datos y especializarse en Oracle este es su momento.")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/oracle.jpg")))
										.build();
				cRepo.save(cursoJava);
				cRepo.save(cursoSpring);
				cRepo.saveAndFlush(cursoOracle);
			}
		};
	}

}
