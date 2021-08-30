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

import com.minhalojadegames.lojagames.models.Categoria;
import com.minhalojadegames.lojagames.repositorys.CategoriaRepository;

@RestController
@RequestMapping("/lojagames/categoria")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired CategoriaRepository categReposit;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAllCategoria(){
		return ResponseEntity.ok(categReposit.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable(value = "id") Long id){
		return categReposit.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{a}")
	public ResponseEntity<List<Categoria>> getByDescricao(@PathVariable(value = "a") String desc){
		List<Categoria> descricao = categReposit.findAllByDescCategoriaContainingIgnoreCase(desc);
		if(descricao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().body(descricao);
		}
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria post){
		return ResponseEntity.status(HttpStatus.CREATED).body(categReposit.save(post));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria put){
		return ResponseEntity.status(HttpStatus.CREATED).body(categReposit.save(put));
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deleteCategoria(@PathVariable(value = "id") Long id) {
		categReposit.deleteById(id);
	}
	
}
