package com.lojaDeGames.boomka.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojaDeGames.boomka.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
	Optional<Usuario> findByEmail(String email);
}
