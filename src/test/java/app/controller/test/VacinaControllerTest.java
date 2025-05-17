package app.controller.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
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

import app.controller.VacinaController;
import app.entity.Vacina;
import app.service.VacinaService;

@SpringBootTest
public class VacinaControllerTest {

    @Autowired
    private VacinaController vacinaController;

    @MockitoBean
    private VacinaService vacinaService;

    private Vacina vacinaValida;
    private Vacina vacinaInvalida;

    @BeforeEach
    void setup() {
        vacinaValida = new Vacina();
        vacinaValida.setId(1L);
        vacinaValida.setNome("Raiva");
        vacinaValida.setDataAplicacao(LocalDate.now());
        vacinaValida.setLote("LOTE123");
        vacinaValida.setDataProximaDose(LocalDate.now().plusYears(1));

        vacinaInvalida = new Vacina();
        vacinaInvalida.setNome("");
        vacinaInvalida.setDataAplicacao(null);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao cadastrar vacina válida")
    void salvarVacinaValida_deveRetornar200() {
        when(vacinaService.save(any(Vacina.class))).thenReturn("Vacina cadastrada com sucesso");

        ResponseEntity<String> response = vacinaController.save(vacinaValida);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vacina cadastrada com sucesso", response.getBody());
        verify(vacinaService, times(1)).save(any(Vacina.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao cadastrar vacina sem nome")
    void salvarVacinaInvalida_deveLancarExcecao() {
        when(vacinaService.save(any(Vacina.class)))
            .thenThrow(new IllegalArgumentException("Nome da vacina é obrigatório"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            vacinaController.save(vacinaInvalida);
        });

        assertEquals("Nome da vacina é obrigatório", exception.getMessage());
        verify(vacinaService, times(1)).save(any(Vacina.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar lista de vacinas ao buscar todas")
    void buscarTodasVacinas_deveRetornarLista() {
        when(vacinaService.findAll()).thenReturn(List.of(vacinaValida));

        ResponseEntity<List<Vacina>> response = vacinaController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        verify(vacinaService, times(1)).findAll();
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar lista vazia ao buscar vacinas por lote inexistente")
    void buscarVacinasPorLoteInexistente_deveRetornarVazio() {
        when(vacinaService.findByLote("LOTE999")).thenReturn(Collections.emptyList());

        ResponseEntity<List<Vacina>> response = vacinaController.findByLote("LOTE999");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(vacinaService, times(1)).findByLote("LOTE999");
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar vacina ao buscar por ID existente")
    void buscarVacinaPorIdExistente_deveRetornarVacina() {
        when(vacinaService.findById(1L)).thenReturn(vacinaValida);

        ResponseEntity<Vacina> response = vacinaController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(vacinaService, times(1)).findById(1L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao buscar vacina com ID inexistente")
    void buscarVacinaInexistente_deveLancarExcecao() {
        when(vacinaService.findById(999L))
            .thenThrow(new RuntimeException("Vacina com ID 999 não encontrada"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            vacinaController.findById(999L);
        });

        assertEquals("Vacina com ID 999 não encontrada", exception.getMessage());
        verify(vacinaService, times(1)).findById(999L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao atualizar vacina existente")
    void atualizarVacinaExistente_deveRetornar200() {
        when(vacinaService.update(eq(1L), any(Vacina.class))).thenReturn("Vacina atualizada");

        ResponseEntity<String> response = vacinaController.update(vacinaValida, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vacina atualizada", response.getBody());
        verify(vacinaService, times(1)).update(eq(1L), any(Vacina.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao atualizar vacina inexistente")
    void atualizarVacinaInexistente_deveLancarExcecao() {
        when(vacinaService.update(eq(999L), any(Vacina.class)))
            .thenThrow(new RuntimeException("Vacina com ID 999 não encontrada"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            vacinaController.update(vacinaValida, 999L);
        });

        assertEquals("Vacina com ID 999 não encontrada", exception.getMessage());
        verify(vacinaService, times(1)).update(eq(999L), any(Vacina.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao deletar vacina existente")
    void deletarVacinaExistente_deveRetornar200() {
        when(vacinaService.delete(1L)).thenReturn("Vacina removida");

        ResponseEntity<String> response = vacinaController.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vacina removida", response.getBody());
        verify(vacinaService, times(1)).delete(1L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao deletar vacina inexistente")
    void deletarVacinaInexistente_deveLancarExcecao() {
        when(vacinaService.delete(999L))
            .thenThrow(new RuntimeException("Vacina com ID 999 não encontrada"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            vacinaController.delete(999L);
        });

        assertEquals("Vacina com ID 999 não encontrada", exception.getMessage());
        verify(vacinaService, times(1)).delete(999L);
    }
}