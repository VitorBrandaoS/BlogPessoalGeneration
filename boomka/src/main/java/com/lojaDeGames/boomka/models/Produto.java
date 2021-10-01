package com.lojaDeGames.boomka.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tb_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank
	private String nome;

	private Long quant = 0L;

	@NotNull
	private float precoDeFabrica = 0;
	@NotNull
	private float valor = 0;

	@ManyToOne
	@JsonIgnoreProperties({"produto"})
	private Categoria categoria;

	@ManyToMany
	@JsonIgnoreProperties({"listaPedido"})
	private List<Pedido> listaPedido = new ArrayList<>();

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getQuant() {
		return quant;
	}

	public void setQuant(Long quant) {
		this.quant = quant;
	}

	public float getPrecoDeFabrica() {
		return precoDeFabrica;
	}

	public void setPrecoDeFabrica(float precoDeFabrica) {
		this.precoDeFabrica = precoDeFabrica;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
