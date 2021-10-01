package com.lojaDeGames.boomka.repositorys;

import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojaDeGames.boomka.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
	public Optional<Produto> findByCodigo(Long id);
}
