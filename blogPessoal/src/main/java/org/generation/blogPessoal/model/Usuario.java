package org.generation.blogPessoal.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(min = 2, max = 100)
	private String nome;
	@NotBlank
	@Size(min = 5, max = 100)
	private String usuario;
	@NotBlank
	@Size(min = 5, max = 100)
	private String senha;

	//Construtor
	public Usuario(Long id, String nome, String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}

	//Construtor Vazio
	public Usuario() {	}

}
