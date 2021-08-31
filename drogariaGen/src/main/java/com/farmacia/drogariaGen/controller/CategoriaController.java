package com.farmacia.drogariaGen.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.farmacia.drogariaGen.model.Categoria;
import com.farmacia.drogariaGen.repository.CategoriaRepository;

@Controller
@RequestMapping("/farmacia/categoria")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired CategoriaRepository repository;
	
	@GetMapping
	public  ResponseEntity <List<Categoria>> findAllCategoria(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/busca/{id}")
	public ResponseEntity <Optional<Categoria>> findByIdCategoria(@Valid @PathVariable(value = "id") Long id){
	return 	ResponseEntity.ok(repository.findById(id));
	}
	
	@GetMapping("/busca/desc/{desc}")
	public ResponseEntity<List<Categoria>> findByDescCategoria(@PathVariable(value = "desc") String desc){
		List<Categoria> descr = repository.findAllByDescCategoriaContainingIgnoreCase(desc);
		if(descr.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(descr);
		}
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria post){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestParam Categoria put){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(put));
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deletarCategoria(@PathVariable(value = "id") Long id) {
		repository.deleteById(id);
	}
}
