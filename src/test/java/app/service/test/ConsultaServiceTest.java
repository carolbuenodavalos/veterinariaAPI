package app.service.test;

import app.entity.Consulta;
import app.entity.Animal;
import app.entity.Medico;
import app.repository.ConsultaRepository;
import app.service.ConsultaService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsultaServiceTest {

    @InjectMocks
    private ConsultaService consultaService;

    @Mock
    private ConsultaRepository consultaRepository;

    private Consulta criarConsulta() {
        Consulta consulta = new Consulta();
        consulta.setDataHora(LocalDateTime.now());
        consulta.setDescricao("Consulta de rotina");
        consulta.setStatus("AGENDADA");
        consulta.setTipoConsulta("ROTINA");
        consulta.setAnimal(new Animal());
        consulta.setMedico(new Medico());
        return consulta;
    }

    @Test
    @DisplayName("TESTE DE UNIDADE - Cenário 01: Salvar consulta do tipo ROTINA")
    void testSalvarConsultaRotina() {
        Consulta consulta = criarConsulta();
        when(consultaRepository.save(consulta)).thenReturn(consulta);

        String resultado = consultaService.save(consulta);
        assertEquals("Consulta salva com sucesso", resultado);
        assertEquals("NORMAL", consulta.getStatusUrgencia());
    }

    @Test
    @DisplayName("TESTE DE UNIDADE - Cenário 02: Salvar consulta do tipo EMERGÊNCIA")
    void testSalvarConsultaEmergencia() {
        Consulta consulta = criarConsulta();
        consulta.setTipoConsulta("EMERGÊNCIA");
        when(consultaRepository.save(consulta)).thenReturn(consulta);

        consultaService.save(consulta);
        assertEquals("URGENTE", consulta.getStatusUrgencia());
    }

    @Test
    @DisplayName("TESTE DE UNIDADE - Cenário 03: Atualizar consulta existente")
    void testAtualizarConsulta() {
        Consulta consulta = criarConsulta();
        when(consultaRepository.save(consulta)).thenReturn(consulta);

        String resultado = consultaService.update(1L, consulta);
        assertEquals("atualizado com sucesso", resultado);
    }

    @Test
    @DisplayName("TESTE DE UNIDADE - Cenário 04: Deletar consulta existente")
    void testDeletarConsulta() {
        doNothing().when(consultaRepository).deleteById(1L);
        String resultado = consultaService.delete(1L);
        assertEquals("deletado com sucesso", resultado);
    }
    
    
    
}