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

import app.controller.MedicoController;
import app.entity.Medico;
import app.service.MedicoService;

@SpringBootTest
public class MedicoControllerTest {

    @Autowired
    private MedicoController medicoController;

    @MockitoBean
    private MedicoService medicoService;

    private Medico medicoValido;
    private Medico medicoInvalido;

    @BeforeEach
    void setup() {
        medicoValido = new Medico();
        medicoValido.setId(1L);
        medicoValido.setNome("Dr. João Silva");
        medicoValido.setNumeroCrm("123456");
        medicoValido.setCpf("123.456.789-09");
        medicoValido.setEspecialidade("Cardiologia");
        medicoValido.setTelefone("(11) 99999-9999");

        medicoInvalido = new Medico();
        medicoInvalido.setNome("Dr. Inválido");
        medicoInvalido.setNumeroCrm("");
        medicoInvalido.setCpf(null);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao cadastrar médico válido")
    void salvarMedicoValido_deveRetornar200() {
        when(medicoService.save(any(Medico.class))).thenReturn("Médico cadastrado com sucesso");

        ResponseEntity<String> response = medicoController.save(medicoValido);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Médico cadastrado com sucesso", response.getBody());
        verify(medicoService, times(1)).save(any(Medico.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao cadastrar médico com CRM inválido")
    void salvarMedicoInvalido_deveLancarExcecao() {
        when(medicoService.save(any(Medico.class)))
            .thenThrow(new IllegalArgumentException("CRM é obrigatório"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            medicoController.save(medicoInvalido);
        });

        assertEquals("CRM é obrigatório", exception.getMessage());
        verify(medicoService, times(1)).save(any(Medico.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar lista de médicos ao buscar todos")
    void buscarTodosMedicos_deveRetornarLista() {
        when(medicoService.findAll()).thenReturn(List.of(medicoValido));

        ResponseEntity<List<Medico>> response = medicoController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        verify(medicoService, times(1)).findAll();
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar lista vazia ao buscar médicos por especialidade inexistente")
    void buscarMedicosPorEspecialidadeInexistente_deveRetornarVazio() {
        when(medicoService.findByEspecialidade("Dermatologia")).thenReturn(Collections.emptyList());

        ResponseEntity<List<Medico>> response = medicoController.findByEspecialidade("Dermatologia");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(medicoService, times(1)).findByEspecialidade("Dermatologia");
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar médico ao buscar por ID existente")
    void buscarMedicoPorIdExistente_deveRetornarMedico() {
        when(medicoService.findById(1L)).thenReturn(medicoValido);

        ResponseEntity<Medico> response = medicoController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(medicoService, times(1)).findById(1L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao buscar médico com ID inexistente")
    void buscarMedicoInexistente_deveLancarExcecao() {
        when(medicoService.findById(999L))
            .thenThrow(new RuntimeException("Médico com ID 999 não encontrado"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            medicoController.findById(999L);
        });

        assertEquals("Médico com ID 999 não encontrado", exception.getMessage());
        verify(medicoService, times(1)).findById(999L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao atualizar médico existente")
    void atualizarMedicoExistente_deveRetornar200() {
        when(medicoService.update(eq(1L), any(Medico.class))).thenReturn("Médico atualizado");

        ResponseEntity<String> response = medicoController.update(medicoValido, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Médico atualizado", response.getBody());
        verify(medicoService, times(1)).update(eq(1L), any(Medico.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao atualizar médico inexistente")
    void atualizarMedicoInexistente_deveLancarExcecao() {
        when(medicoService.update(eq(999L), any(Medico.class)))
            .thenThrow(new RuntimeException("Médico com ID 999 não encontrado"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            medicoController.update(medicoValido, 999L);
        });

        assertEquals("Médico com ID 999 não encontrado", exception.getMessage());
        verify(medicoService, times(1)).update(eq(999L), any(Medico.class));
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve retornar status 200 ao deletar médico existente")
    void deletarMedicoExistente_deveRetornar200() {
        when(medicoService.delete(1L)).thenReturn("Médico removido");

        ResponseEntity<String> response = medicoController.deleteById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Médico removido", response.getBody());
        verify(medicoService, times(1)).delete(1L);
    }

    @Test
    @DisplayName("TESTE DE INTEGRAÇÃO - Deve lançar exceção ao deletar médico inexistente")
    void deletarMedicoInexistente_deveLancarExcecao() {
        when(medicoService.delete(999L))
            .thenThrow(new RuntimeException("Médico com ID 999 não encontrado"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            medicoController.deleteById(999L);
        });

        assertEquals("Médico com ID 999 não encontrado", exception.getMessage());
        verify(medicoService, times(1)).delete(999L);
    }
}