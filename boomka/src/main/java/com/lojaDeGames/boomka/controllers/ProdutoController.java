package com.lojaDeGames.boomka.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojaDeGames.boomka.models.Produto;
import com.lojaDeGames.boomka.repositorys.ProdutoRepository;

@RestController
@RequestMapping("/boomka/produto")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAllProduto(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable(value = "id") Long id){
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{a}")
	public ResponseEntity<List<Produto>> getByDescricao(@PathVariable(value = "a") String desc){
		List<Produto> descricao = produtoRepository.findAllByNomeContainingIgnoreCase(desc);
		if(descricao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().body(descricao);
		}
	}
	
	@PostMapping
	public ResponseEntity<Produto> adicionarProduto(@Valid @RequestBody Produto post){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(post));
	}
	
	@PutMapping
	public ResponseEntity<Produto> AtualizarProduto(@Valid @RequestBody Produto put){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(put));
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deletarProduto(@PathVariable(value = "id") Long id) {
		produtoRepository.deleteById(id);
	}
}
