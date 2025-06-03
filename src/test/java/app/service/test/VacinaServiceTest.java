package app.service.test;

import app.entity.Vacina;
import app.repository.VacinaRepository;
import app.service.VacinaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VacinaServiceTest {

    @InjectMocks
    private VacinaService vacinaService;

    @Mock
    private VacinaRepository vacinaRepository;

    private Vacina novaVacina() {
        Vacina vacina = new Vacina();
        vacina.setNome("Antirrábica");
        vacina.setLote("123");
        vacina.setDataValidade(LocalDate.now());
        return vacina;
    }

    @Test
    @DisplayName("Cenário 01 – Deve salvar vacina com sucesso")
    void testSalvarVacina() {
        Vacina vacina = novaVacina();
        when(vacinaRepository.save(vacina)).thenReturn(vacina);

        String resultado = vacinaService.save(vacina);
        assertEquals("Nova vacina salva com sucesso", resultado);
    }

    @Test
    @DisplayName("Cenário 02 – Deve atualizar vacina existente com sucesso")
    void testAtualizarVacina() {
        Vacina vacina = novaVacina();
        when(vacinaRepository.findById(1L)).thenReturn(Optional.of(vacina));
        when(vacinaRepository.save(vacina)).thenReturn(vacina);

        String resultado = vacinaService.update(1L, vacina);
        assertEquals("atualizado com sucesso", resultado);
    }

    @Test
    @DisplayName("Cenário 03 – Deve lançar exceção ao atualizar vacina inexistente")
    void testAtualizarVacinaInexistente() {
        when(vacinaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            vacinaService.update(999L, novaVacina());
        });
    }

    @Test
    @DisplayName("Cenário 04 – Deve buscar vacina por ID com sucesso")
    void testBuscarPorId() {
        Vacina vacina = novaVacina();
        when(vacinaRepository.findById(1L)).thenReturn(Optional.of(vacina));

        Vacina resultado = vacinaService.findById(1L);
        assertNotNull(resultado);
        assertEquals("Antirrábica", resultado.getNome());
    }

    @Test
    @DisplayName("Cenário 05 – Deve deletar vacina com sucesso")
    void testDeletarVacina() {
        Vacina vacina = novaVacina();
        when(vacinaRepository.findById(1L)).thenReturn(Optional.of(vacina));
        doNothing().when(vacinaRepository).deleteById(1L);

        String resultado = vacinaService.delete(1L);
        assertEquals("Vacina deletada com sucesso", resultado);
    }

    @Test
    @DisplayName("Cenário 06 – Deve lançar exceção ao deletar vacina inexistente")
    void testDeletarVacinaInexistente() {
        when(vacinaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            vacinaService.delete(999L);
        });
    }
    
    
}

