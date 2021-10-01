package com.lojaDeGames.boomka.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jdk.jfr.Enabled;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnoreProperties({"listaPedidoUsuario", "senha"})
    private Usuario usuario;
    @ManyToMany
    @JsonIgnoreProperties({"listaPedido", "id", "descCategoria"})
    private List<Produto> listaProdutos = new ArrayList<>();
    private float totalValorVenda;
    private String statusVenda = "Em andamento";

    //------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public String getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }

    public float getTotalValorVenda() {
        return totalValorVenda;
    }

    public void setTotalValorVenda(float totalValorVenda) {
        this.totalValorVenda = totalValorVenda;
    }
}
