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

import app.controller.AnimalController;
import app.entity.Animal;
import app.entity.Tutor;
import app.service.AnimalService;

@SpringBootTest
public class AnimalControllerTest {

    @Autowired
    private AnimalController animalController;

    @MockitoBean
    private AnimalService animalService;

    private Animal animalValido;
    private Animal animalInvalido;

    @BeforeEach
    void setup() {
        Tutor tutorValido = new Tutor();
        tutorValido.setId(1L);
        tutorValido.setNome("Tutor Válido");

        animalValido = new Animal();
        animalValido.setNome("Rex");
        animalValido.setEspecie("Cão");
        animalValido.setTutor(tutorValido);
        
        animalInvalido = new Animal();
        animalInvalido.setNome("Felix");
        animalInvalido.setEspecie("Gato");
        animalInvalido.setTutor(null);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 201 ao cadastrar animal com tutor válido")
    void salvarAnimalValido_deveRetornar201() {
        when(animalService.save(any(Animal.class))).thenReturn("Animal salvo com sucesso");

        ResponseEntity<String> response = animalController.save(animalValido);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Animal salvo com sucesso", response.getBody());
        verify(animalService, times(1)).save(any(Animal.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao cadastrar animal sem tutor")
    void salvarAnimalSemTutor_deveLancarExcecao() {
        when(animalService.save(any(Animal.class)))
            .thenThrow(new IllegalArgumentException("Tutor é obrigatório"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            animalController.save(animalInvalido);
        });

        assertEquals("Tutor é obrigatório", exception.getMessage());
        verify(animalService, times(1)).save(any(Animal.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar lista vazia ao buscar animal por nome inexistente")
    void buscarPorNomeInexistente_deveRetornarListaVazia() {
        when(animalService.findByNome("inexistente")).thenReturn(Collections.emptyList());

        ResponseEntity<List<Animal>> response = animalController.findByNome("inexistente");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(animalService, times(1)).findByNome("inexistente");
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao buscar animal com ID inexistente")
    void buscarAnimalInexistente_deveLancarExcecao() {
        when(animalService.findById(999L))
            .thenThrow(new RuntimeException("Animal com ID 999 não encontrado"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            animalController.findById(999L);
        });

        assertEquals("Animal com ID 999 não encontrado", exception.getMessage());
        verify(animalService, times(1)).findById(999L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao atualizar animal existente")
    void atualizarAnimalValido_deveRetornar200() {
        when(animalService.update(eq(1L), any(Animal.class))).thenReturn("Animal atualizado");

        ResponseEntity<String> response = animalController.update(animalValido, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Animal atualizado", response.getBody());
        verify(animalService, times(1)).update(eq(1L), any(Animal.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao atualizar animal inexistente")
    void atualizarAnimalInexistente_deveLancarExcecao() {
        when(animalService.update(eq(999L), any(Animal.class)))
            .thenThrow(new RuntimeException("Animal com ID 999 não encontrado"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            animalController.update(animalValido, 999L);
        });

        assertEquals("Animal com ID 999 não encontrado", exception.getMessage());
        verify(animalService, times(1)).update(eq(999L), any(Animal.class));
    }
}