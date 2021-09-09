package com.lojaDeGames.boomka.services;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lojaDeGames.boomka.models.Usuario;
import com.lojaDeGames.boomka.models.utilities.Login;
import com.lojaDeGames.boomka.repositorys.UsuarioRepository;

@Service
public class LoginService {

	private @Autowired UsuarioRepository usuarioRepository;

	public Optional<Object> cadastrarUsuario(Usuario novoUsuario) {
		return usuarioRepository.findByEmail(novoUsuario.getEmail()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String result = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(result);
			return Optional.ofNullable(usuarioRepository.save(novoUsuario));
		});
	}

	public Optional<?> pegarCredenciais(Login usuarioAutenticador) {
		return usuarioRepository.findByEmail(usuarioAutenticador.getEmail()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(usuarioAutenticador.getSenha(), usuarioExistente.getSenha())) {
				String estruturaBasic = usuarioAutenticador.getEmail() + ":" + usuarioAutenticador.getSenha();
				
				byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII")));
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64); 

				usuarioAutenticador.setToken(autorizacaoHeader);
				usuarioAutenticador.setId(usuarioExistente.getId());
				usuarioAutenticador.setNome(usuarioExistente.getNome());
				usuarioAutenticador.setSenha(usuarioExistente.getSenha());
				return Optional.ofNullable(usuarioAutenticador);
			
		} else {
			return Optional.empty();
		}
		}).orElseGet(() -> {
			return Optional.empty();
		});
		
	}
}