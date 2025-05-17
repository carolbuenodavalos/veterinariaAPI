package app.controller.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import app.controller.TutorController;
import app.entity.Tutor;
import app.service.TutorService;

@SpringBootTest
public class TutorControllerTest {

    @Autowired
    private TutorController tutorController;

    @MockitoBean
    private TutorService tutorService;

    private Tutor tutorValido;
    private Tutor tutorInvalido;

    @BeforeEach
    void setup() {
        tutorValido = new Tutor();
        tutorValido.setId(1L);
        tutorValido.setNome("João Silva");
        tutorValido.setCpf("123.456.789-09");
        tutorValido.setTelefone("(11) 99999-9999");

        tutorInvalido = new Tutor();
        tutorInvalido.setNome("Maria");
        tutorInvalido.setCpf("123");
        tutorInvalido.setTelefone("");
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao cadastrar tutor válido")
    void salvarTutorValido_deveRetornar200() {
        when(tutorService.save(any(Tutor.class))).thenReturn("Tutor cadastrado com sucesso");

        ResponseEntity<String> response = tutorController.save(tutorValido);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tutor cadastrado com sucesso", response.getBody());
        verify(tutorService, times(1)).save(any(Tutor.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao cadastrar tutor com CPF inválido")
    void salvarTutorInvalido_deveLancarExcecao() {
        when(tutorService.save(any(Tutor.class)))
            .thenThrow(new IllegalArgumentException("CPF inválido"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tutorController.save(tutorInvalido);
        });

        assertEquals("CPF inválido", exception.getMessage());
        verify(tutorService, times(1)).save(any(Tutor.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar lista de tutores ao buscar todos")
    void buscarTodosTutores_deveRetornarLista() {
        when(tutorService.findAll()).thenReturn(List.of(tutorValido));

        ResponseEntity<List<Tutor>> response = tutorController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        verify(tutorService, times(1)).findAll();
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar lista vazia ao buscar tutores por CPF inexistente")
    void buscarTutoresPorCpfInexistente_deveRetornarVazio() {
        when(tutorService.findByCpf("000.000.000-00")).thenReturn(Collections.emptyList());

        ResponseEntity<List<Tutor>> response = tutorController.findByCpf("000.000.000-00");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(tutorService, times(1)).findByCpf("000.000.000-00");
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar tutor ao buscar por ID existente")
    void buscarTutorPorIdExistente_deveRetornarTutor() {
        when(tutorService.findById(1L)).thenReturn(tutorValido);

        ResponseEntity<Tutor> response = tutorController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(tutorService, times(1)).findById(1L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao buscar tutor com ID inexistente")
    void buscarTutorInexistente_deveLancarExcecao() {
        when(tutorService.findById(999L))
            .thenThrow(new RuntimeException("Tutor com ID 999 não encontrado"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tutorController.findById(999L);
        });

        assertEquals("Tutor com ID 999 não encontrado", exception.getMessage());
        verify(tutorService, times(1)).findById(999L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao atualizar tutor existente")
    void atualizarTutorExistente_deveRetornar200() {
        when(tutorService.update(eq(1L), any(Tutor.class))).thenReturn("Tutor atualizado");

        ResponseEntity<String> response = tutorController.update(tutorValido, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tutor atualizado", response.getBody());
        verify(tutorService, times(1)).update(eq(1L), any(Tutor.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao atualizar tutor inexistente")
    void atualizarTutorInexistente_deveLancarExcecao() {
        when(tutorService.update(eq(999L), any(Tutor.class)))
            .thenThrow(new RuntimeException("Tutor com ID 999 não encontrado"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tutorController.update(tutorValido, 999L);
        });

        assertEquals("Tutor com ID 999 não encontrado", exception.getMessage());
        verify(tutorService, times(1)).update(eq(999L), any(Tutor.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao deletar tutor existente")
    void deletarTutorExistente_deveRetornar200() {
        when(tutorService.delete(1L)).thenReturn("Tutor removido");

        ResponseEntity<String> response = tutorController.deleteById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tutor removido", response.getBody());
        verify(tutorService, times(1)).delete(1L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao deletar tutor inexistente")
    void deletarTutorInexistente_deveLancarExcecao() {
        when(tutorService.delete(999L))
            .thenThrow(new RuntimeException("Tutor com ID 999 não encontrado"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tutorController.deleteById(999L);
        });

        assertEquals("Tutor com ID 999 não encontrado", exception.getMessage());
        verify(tutorService, times(1)).delete(999L);
    }
}