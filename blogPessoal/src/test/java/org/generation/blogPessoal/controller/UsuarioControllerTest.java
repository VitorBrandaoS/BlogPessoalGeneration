package org.generation.blogPessoal.controller;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//Define a ordem em que os testes serão executados, caso não tivesse setado esta anotação a IDE iria executar na ordem alfabética.
public class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;
    private Usuario usuarioUpdate;
    private Usuario usuarioAdmin;

    @BeforeAll
    public void start(){
        usuarioAdmin = new Usuario(0L, "Administrador", "admin", "admin321");
        if (!usuarioRepository.findByUsuario(usuarioAdmin.getUsuario()).isPresent()) {
            HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAdmin);
            testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
        }

        usuario = new Usuario(0L, "Manuel Silva", "Xirmeu", "4213423");

        usuarioUpdate = new Usuario(2L, "Frederico Bonseio", "Bonseio", "08323942");

    }

    @Test
    @DisplayName("✔ Cadastrar Usuário!")
    @Order(1)
    public void deveRealizarPostUsuario(){
        HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
        ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    }

    @Test
    @DisplayName("👍 Listar todos Usuários!")
    @Order(2)
    public void deveRealizarGetAllUsuario(){
        ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("admin", "admin321").exchange("/usuarios", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    @DisplayName("🖐 Alterar Usuários!")
    @Order(3)
    public void deveRealizarPutUsuario(){
        HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioUpdate);
        ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("admin", "admin321").exchange("/usuarios", HttpMethod.PUT, request, Usuario.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

}
