package com.minhalojadegames.lojagames.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe para armazenar informações de categoria
 * nome da categoria
 * descrição da categoria
 * e seus respectivos produtos
 * @author Vitor
 * @since 1.0
 */

@Entity
@Table(name = "tb_categoria")
public class Categoria {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String nomeCategoria;
	
	@NotBlank
	@Size(max = 80)
	private String descCategoria;

	@OneToMany(mappedBy = "idCategoria", cascade = CascadeType.REMOVE)
	//@JoinColumn(name = "tb_produtos")
	@JsonIgnoreProperties("categoria")
	private List<Produto> produto;// = new ArrayList<>()
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getDescCategoria() {
		return descCategoria;
	}

	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}	
	
}
