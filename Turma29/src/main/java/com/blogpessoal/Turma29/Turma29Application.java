package com.blogpessoal.Turma29;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class Turma29Application {

	@GetMapping
	public String menu() {
		return "Para acessar meu primeiro App: "
				+ "</br><a href = \"/app_1\"> localhost:8080/app_1 </a>"
				+ "</br>Para acessar meu segundo App:"
				+ "</br><a href = \"/app_2\"> localhost:8080/app_2 </a>";
	}
	
	@GetMapping("/app_1")
	public String minhaPrimeiraApp(){
		return "Hello World!</br>"
				+ "</br>Mentalidades:</br>1-Mentalidade de Crescimento</br>2-Persistência</br>3-Orientação ao Futuro</br>"
				+ "</br>Habilidades:</br>1-Atenção aos detalhes</br>2-Proatividade"
				+ "</br></br><a href = \"/\">Voltar</a>";
	}
	
	@GetMapping("/app_2")
	public String minhaSegundaApp() {
		return "Hello World!</br>"
				+ "</br>Meus objetivos de aprendizagem para está semana:"
				+ "</br>-Conseguir dominar o conceito de Spring Boot.</br>-Aprender a criar meu próprio \"Maven\", sem auxilio de sites online."
				+ "</br></br><a href = \"/\">Voltar</a>";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Turma29Application.class, args);
	}

}
