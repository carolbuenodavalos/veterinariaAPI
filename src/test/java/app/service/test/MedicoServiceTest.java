package app.service.test;

import app.entity.Medico;
import app.repository.MedicoRepository;
import app.service.MedicoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicoServiceTest {

    @InjectMocks
    private MedicoService medicoService;

    @Mock
    private MedicoRepository medicoRepository;

    private Medico criarMedico() {
        Medico medico = new Medico();
        medico.setNome("Dr. Silva");
        medico.setNumeroCrm("123456");
        medico.setCpf("123.456.789-00");
        medico.setEspecialidade("Cardiologista");
        medico.setTelefone("(11) 99999-9999");
        return medico;
    }

    @Test
    @DisplayName("TESTE DE UNIDADE - Cenário 01: Cadastrar médico com dados válidos")
    void testSalvarMedico() {
        Medico medico = criarMedico();
        when(medicoRepository.save(medico)).thenReturn(medico);

        String resultado = medicoService.save(medico);
        assertEquals("Salvo com sucesso", resultado);
    }

    @Test
    @DisplayName("TESTE DE UNIDADE - Cenário 02: Atualizar médico existente")
    void testAtualizarMedicoExistente() {
        Medico medico = criarMedico();
        when(medicoRepository.save(medico)).thenReturn(medico);

        String resultado = medicoService.update(1L, medico);
        assertEquals("atualizado com sucesso", resultado);
    }


}