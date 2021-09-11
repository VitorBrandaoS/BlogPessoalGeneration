package org.generation.blogPessoal.repository;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//O ciclo de vida do teste ao invez de ser por metodos tem de ser por classes quando utilizamos o BeforeAll
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
//Vai executar apenas uma vez e depois vão ser executados os demais testes.
    void start() {
        Usuario usuario = new Usuario(0L, "João Brabo", "Brabo", "12345678");

        if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            usuarioRepository.save(usuario);
        }

        usuario = new Usuario(0L, "Manuel Silva", "Xirmeu", "4213423");

        if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            usuarioRepository.save(usuario);
        }

        usuario = new Usuario(0L, "Frederico Bonseio", "Bonseio", "08323942");

        if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            usuarioRepository.save(usuario);
        }

        usuario = new Usuario(0L, "Paulo Lacnes", "Lacunes", "32142142");

        if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            usuarioRepository.save(usuario);
        }

    }

    @Test
    @DisplayName("💾 Retorna Nome")
    public void findByRetornaNome(){

        Usuario usuario = usuarioRepository.findByNome("João Brabo");
        assertTrue(usuario.getNome().equals("João Brabo"));
    }

    @Test
    @DisplayName("💾 Retorna 2 Usuários")
    public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios(){

        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("b");
        assertEquals(2, listaDeUsuarios.size());
    }

    @AfterAll//Executa após a execução de todos os testes.
    public void end() {
        /*Poderia criar um metodo para apagar todo o banco no fim dos testes.*/
        System.out.println("Teste foi Finalizado!");
    }
}
