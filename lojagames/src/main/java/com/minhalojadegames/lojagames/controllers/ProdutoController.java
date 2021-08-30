package com.minhalojadegames.lojagames.controllers;

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

import com.minhalojadegames.lojagames.models.Produto;
import com.minhalojadegames.lojagames.repositorys.ProdutoRepository;

@RestController
@RequestMapping("/lojagames/produto")
@CrossOrigin("*")
public class ProdutoController {
	
@Autowired ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAllProduto(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable(value = "id") Long id){
		return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{a}")
	public ResponseEntity<List<Produto>> getByDescricao(@PathVariable(value = "a") String desc){
		List<Produto> descricao = repository.findAllByNomeContainingIgnoreCase(desc);
		if(descricao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().body(descricao);
		}
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto post){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto put){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(put));
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deleteProduto(@PathVariable(value = "id") Long id) {
		repository.deleteById(id);
	}
}
