package com.minhalojadegames.lojagames.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhalojadegames.lojagames.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public List<Produto> findAllByNomeContainingIgnoreCase(String desc);
}
