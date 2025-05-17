package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Animal;
import app.repository.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	private AnimalRepository animalRepository;
	
	
    public String save(Animal animal) {
        // Regra de negócio com excecao
        if (animal.getTutor() == null) {
        }
        this.animalRepository.save(animal);
        return "Animal salvo com sucesso";
    }


	public String update(long id, Animal animal) {
		animal.setId(id);
		this.animalRepository.save(animal); 
		return "atualizado com sucesso";
	}

    
	// AnimalService.java
	public String delete(long id) {
	    if (!animalRepository.existsById(id)) {
	        throw new RuntimeException("Animal não encontrado com o ID: " + id); // Mantenha para os testes unitários
	    }
	    this.animalRepository.deleteById(id);
	    return "deletado com sucesso";
	}
	
	
	public Animal findById(long id) {
	    return this.animalRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Animal com ID " + id + " não encontrado"));
	}

	
    
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public List<Animal> findByNome(String nome) {
        return animalRepository.findByNomeIgnoreCaseContaining(nome);
    }

    public List<Animal> findByEspecie(String especie) {
        return animalRepository.findByEspecie(especie);
    }

}