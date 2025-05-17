package app.service.test;

import app.entity.Tutor;
import app.repository.TutorRepository;
import app.service.TutorService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TutorServiceTest {

    @InjectMocks
    private TutorService tutorService;

    @Mock
    private TutorRepository tutorRepository;

    private Tutor criarTutor() {
        Tutor tutor = new Tutor();
        tutor.setNome("João Silva");
        tutor.setCpf("123.456.789-00");
        tutor.setTelefone("(11) 99999-9999");
        return tutor;
    }

        @Test
        @DisplayName("TESTE DE UNIDADE - Cenário 01: Cadastrar tutor com dados válidos")
        void testSalvarTutor() {
            Tutor tutor = criarTutor();
            when(tutorRepository.save(tutor)).thenReturn(tutor);

            String resultado = tutorService.save(tutor);
            assertEquals("salvo com sucesso", resultado);
        }

        @Test
        @DisplayName("TESTE DE UNIDADE - Cenário 02: Atualizar tutor existente")
        void testAtualizarTutorExistente() {
            Tutor tutor = criarTutor();
            when(tutorRepository.save(tutor)).thenReturn(tutor);

            String resultado = tutorService.update(1L, tutor);
            assertEquals("atualizado com sucesso", resultado);
        }

    }