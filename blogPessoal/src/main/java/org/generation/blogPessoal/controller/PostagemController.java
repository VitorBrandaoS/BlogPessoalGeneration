package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
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

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscarPorId(@PathVariable(value = "id") long id) {
		Optional<Postagem> objetoPostagem = repository.findById(id);

		if (objetoPostagem.isPresent()) {
			return ResponseEntity.status(200).body(objetoPostagem.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	/*
	 * public ResponseEntity<Postagem> GetById(@PathVariable long id) { return
	 * repository.findById(id).map(resp ->
	 * ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	 * 
	 * }
	 */

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> buscaPorTitulo(@PathVariable(value = "titulo") String title) {
		List<Postagem> objetoLista = repository.findAllByTituloContainingIgnoreCase(title);
		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping 
	public ResponseEntity<Postagem>
	 post(@Valid @RequestBody Postagem post){ 
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
	}

	@PutMapping
	public ResponseEntity<Postagem> atualizar(@Valid @RequestBody Postagem usuarioParaAtualizar) {
		return ResponseEntity.status(201).body(repository.save(usuarioParaAtualizar));
	}

	@DeleteMapping("/{id_post}")
	public void deletar(@PathVariable(value = "id_post") Long id_post) {
		repository.deleteById(id_post);
	}

}
