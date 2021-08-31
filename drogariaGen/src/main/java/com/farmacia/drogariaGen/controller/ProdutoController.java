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

import com.farmacia.drogariaGen.model.Produto;
import com.farmacia.drogariaGen.repository.ProdutoRepository;


@Controller
@RequestMapping("/farmacia/produtos")
@CrossOrigin("*")
public class ProdutoController {
	 @Autowired ProdutoRepository repository2;
	 
	 @GetMapping
		public  ResponseEntity <List<Produto>> findAllProduto(){
			return ResponseEntity.ok(repository2.findAll());
		}
		
		@GetMapping("/busca/{id}")
		public ResponseEntity <Optional<Produto>> findByIdProduto(@Valid @PathVariable(value = "id") Long id){
		return 	ResponseEntity.ok(repository2.findById(id));
		}
		
		@GetMapping("/busca/desc/{desc}")
		public ResponseEntity<List<Produto>> findByDescProduto(@PathVariable(value = "desc") String desc){
			List<Produto> descr = repository2.findAllByNomeContainingIgnoreCase(desc);
			if(descr.isEmpty()) {
				return ResponseEntity.notFound().build();
			}else {
				return ResponseEntity.ok().body(descr);
			}
		}
		
		@PostMapping
		public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto post){
			return ResponseEntity.status(HttpStatus.CREATED).body(repository2.save(post));
		}
		
		@PutMapping
		public ResponseEntity<Produto> putProduto(@Valid @RequestParam Produto put){
			return ResponseEntity.status(HttpStatus.CREATED).body(repository2.save(put));
		}
		
		@DeleteMapping("/deletar/{id}")
		public void deletarProduto(@PathVariable(value = "id") Long id) {
			repository2.deleteById(id);
			}
}
