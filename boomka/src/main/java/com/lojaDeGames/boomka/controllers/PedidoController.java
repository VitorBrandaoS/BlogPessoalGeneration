package com.lojaDeGames.boomka.controllers;

import com.lojaDeGames.boomka.models.Pedido;
import com.lojaDeGames.boomka.models.Produto;
import com.lojaDeGames.boomka.repositorys.PedidoRepository;
import com.lojaDeGames.boomka.repositorys.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/boomka/pedido")
@CrossOrigin("*")
public class PedidoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedido(){
        return ResponseEntity.ok(pedidoRepository.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable(name = "id") Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(pedido.get());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Pedido> cadastrarPedido(@Valid @RequestBody Pedido pedido){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(pedido));
    }

    @PostMapping("/cadastrarProduto/{id_pedido}/{id_produto}")
    public ResponseEntity<?> cadastrarProduto(@PathVariable(name = "id_pedido") Long idPedido, @PathVariable(name = "id_produto") Long idProduto){
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        Optional<Produto>produto = produtoRepository.findByCodigo(idProduto);
        if (pedido.isPresent() && produto.isPresent()){
            if (pedido.get().getListaProdutos().contains(produto.get())){
                produto.get().setQuant(produto.get().getQuant()+1L);
                produto.get().setValor(produto.get().getQuant() * produto.get().getPrecoDeFabrica());
            }else {
                produto.get().setQuant(1L);
                produto.get().setValor(produto.get().getQuant() * produto.get().getPrecoDeFabrica());
                pedido.get().getListaProdutos().add(produto.get());
            }
            return ResponseEntity.ok().body(pedidoRepository.save(pedido.get()));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    public void calculoTotal(){

    }


}
