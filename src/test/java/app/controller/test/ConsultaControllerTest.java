package app.controller.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
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

import app.controller.ConsultaController;
import app.entity.Animal;
import app.entity.Consulta;
import app.entity.Medico;
import app.service.ConsultaService;

@SpringBootTest
public class ConsultaControllerTest {

    @Autowired
    private ConsultaController consultaController;

    @MockitoBean
    private ConsultaService consultaService;

    private Consulta consultaValida;
    private Consulta consultaInvalida;

    @BeforeEach
    void setup() {
        Animal animal = new Animal();
        animal.setId(1L);
        
        Medico medico = new Medico();
        medico.setId(1L);

        consultaValida = new Consulta();
        consultaValida.setId(1L);
        consultaValida.setDataHora(LocalDateTime.now().plusDays(1));
        consultaValida.setDescricao("Consulta de rotina");
        consultaValida.setStatus("AGENDADA");
        consultaValida.setTipoConsulta("ROTINA");
        consultaValida.setAnimal(animal);
        consultaValida.setMedico(medico);

        consultaInvalida = new Consulta();
        consultaInvalida.setDataHora(null);
        consultaInvalida.setDescricao("");
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao cadastrar consulta válida")
    void salvarConsultaValida_deveRetornar200() {
        when(consultaService.save(any(Consulta.class))).thenReturn("Consulta agendada com sucesso");

        ResponseEntity<String> response = consultaController.save(consultaValida);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Consulta agendada com sucesso", response.getBody());
        verify(consultaService, times(1)).save(any(Consulta.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao cadastrar consulta sem data/hora")
    void salvarConsultaInvalida_deveLancarExcecao() {
        when(consultaService.save(any(Consulta.class)))
            .thenThrow(new IllegalArgumentException("Data e hora são obrigatórias"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            consultaController.save(consultaInvalida);
        });

        assertEquals("Data e hora são obrigatórias", exception.getMessage());
        verify(consultaService, times(1)).save(any(Consulta.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar lista de consultas ao buscar todas")
    void buscarTodasConsultas_deveRetornarLista() {
        when(consultaService.findAll()).thenReturn(List.of(consultaValida));

        ResponseEntity<List<Consulta>> response = consultaController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        verify(consultaService, times(1)).findAll();
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar lista vazia ao buscar consultas por status inexistente")
    void buscarConsultasPorStatusInexistente_deveRetornarVazio() {
        when(consultaService.findByStatus("CANCELADA")).thenReturn(Collections.emptyList());

        ResponseEntity<List<Consulta>> response = consultaController.findByStatus("CANCELADA");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(consultaService, times(1)).findByStatus("CANCELADA");
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar consulta ao buscar por ID existente")
    void buscarConsultaPorIdExistente_deveRetornarConsulta() {
        when(consultaService.findById(1L)).thenReturn(consultaValida);

        ResponseEntity<Consulta> response = consultaController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(consultaService, times(1)).findById(1L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao buscar consulta com ID inexistente")
    void buscarConsultaInexistente_deveLancarExcecao() {
        when(consultaService.findById(999L))
            .thenThrow(new RuntimeException("Consulta com ID 999 não encontrada"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaController.findById(999L);
        });

        assertEquals("Consulta com ID 999 não encontrada", exception.getMessage());
        verify(consultaService, times(1)).findById(999L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao atualizar consulta existente")
    void atualizarConsultaExistente_deveRetornar200() {
        when(consultaService.update(eq(1L), any(Consulta.class))).thenReturn("Consulta atualizada");

        ResponseEntity<String> response = consultaController.update(1L, consultaValida);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Consulta atualizada", response.getBody());
        verify(consultaService, times(1)).update(eq(1L), any(Consulta.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao atualizar consulta inexistente")
    void atualizarConsultaInexistente_deveLancarExcecao() {
        when(consultaService.update(eq(999L), any(Consulta.class)))
            .thenThrow(new RuntimeException("Consulta com ID 999 não encontrada"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaController.update(999L, consultaValida);
        });

        assertEquals("Consulta com ID 999 não encontrada", exception.getMessage());
        verify(consultaService, times(1)).update(eq(999L), any(Consulta.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao deletar consulta existente")
    void deletarConsultaExistente_deveRetornar200() {
        when(consultaService.delete(1L)).thenReturn("Consulta cancelada");

        ResponseEntity<String> response = consultaController.deleteById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Consulta cancelada", response.getBody());
        verify(consultaService, times(1)).delete(1L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao deletar consulta inexistente")
    void deletarConsultaInexistente_deveLancarExcecao() {
        when(consultaService.delete(999L))
            .thenThrow(new RuntimeException("Consulta com ID 999 não encontrada"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaController.deleteById(999L);
        });

        assertEquals("Consulta com ID 999 não encontrada", exception.getMessage());
        verify(consultaService, times(1)).delete(999L);
    }
}