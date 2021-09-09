package com.lojaDeGames.boomka.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lojaDeGames.boomka.models.Usuario;
import com.lojaDeGames.boomka.repositorys.UsuarioRepository;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {
	private @Autowired UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> objetoOptional = usuarioRepository.findByEmail(username);

		if (objetoOptional.isPresent()) {
			return new UserDetailsImplements(objetoOptional.get());
		} else {
			throw new UsernameNotFoundException(username + " não existe!");
		}
	}
	
	
}
