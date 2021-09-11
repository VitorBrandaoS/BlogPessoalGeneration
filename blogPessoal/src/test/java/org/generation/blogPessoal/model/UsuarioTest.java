package org.generation.blogPessoal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

    public Usuario usuario;
    public Usuario usuarioNulo = new Usuario();

    @Autowired
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    Validator validator = factory.getValidator();

    @Test
    void testNaoValidaAtributos() {
        Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioNulo);

        System.out.println(violacao.toString());

        assertFalse(violacao.isEmpty());

    }

    @BeforeEach //Executa esse teste antes de cada um, sempre chama o BeforeEach antes dos demais.
    public void start() {

        //LocalData data = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        usuario = new Usuario(0L, "João Brabo", "Dominus", "12345678");
    }

    @Test
    void testValidaAtributos() {
        Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);

        System.out.println(violacao.toString());

        assertTrue(violacao.isEmpty());

    }
}
