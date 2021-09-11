package org.generation.blogPessoal.model;

import lombok.Data;

@Data
public class UserLogin {
	
	private String nome;
	
	private String usuario;
	
	private String senha;
	
	private String token;

}
