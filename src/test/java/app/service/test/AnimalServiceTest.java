package app.service.test;

import app.entity.Animal;
import app.entity.Tutor;
import app.repository.AnimalRepository;
import app.service.AnimalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @InjectMocks
    private AnimalService animalService;

    @Mock
    private AnimalRepository animalRepository;

    private Animal novoAnimal() {
        Animal animal = new Animal();
        animal.setNome("Rex");
        animal.setEspecie("Cão");
        animal.setRaca("Labrador");
        animal.setIdade("5 anos");
        animal.setPeso("30 kg");
        animal.setTutor(new Tutor());
        return animal;
    }


    @Test
    @DisplayName("TESTE DE UNIDADE - Cenário 01: Salvar animal com dados válidos")
    void testSalvarAnimalComDadosValidos() {
        Animal animal = novoAnimal();
        when(animalRepository.save(animal)).thenReturn(animal);

        String resultado = animalService.save(animal);
        assertEquals("Animal salvo com sucesso", resultado);
    }

    @Test
    @DisplayName("TESTE DE UNIDADE - Cenário 02: Atualizar animal existente com dados válidos")
    void testAtualizarAnimalExistente() {
        Animal animal = novoAnimal();
        when(animalRepository.save(animal)).thenReturn(animal);

        String resultado = animalService.update(1L, animal);
        assertEquals("atualizado com sucesso", resultado);
    }


    @Test
    @DisplayName("TESTE DE UNIDADE - Cenário 03: Deletar animal existente")
    void testDeletarAnimalExistente() {
        when(animalRepository.existsById(1L)).thenReturn(true);
        doNothing().when(animalRepository).deleteById(1L);

        String resultado = animalService.delete(1L);
        assertEquals("deletado com sucesso", resultado);
    }

    @Test
    @DisplayName("TESTE DE UNIDADE - Cenário 04: Tentativa de deletar animal inexistente que lança exceção")
    void testDeletarAnimalInexistente() {
        when(animalRepository.existsById(999L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            animalService.delete(999L);
        });
    }
}