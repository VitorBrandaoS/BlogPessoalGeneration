package com.lojaDeGames.boomka.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@Email
	private String email;
	@NotBlank
	@Size(min = 5, max = 100)
	private String senha;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private List<Pedido> listaPedidoUsuario;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Pedido> getListaPedidoUsuario() {
		return listaPedidoUsuario;
	}
	public void setListaPedidoUsuario(List<Pedido> listaPedidoUsuario) {
		this.listaPedidoUsuario = listaPedidoUsuario;
	}
}
