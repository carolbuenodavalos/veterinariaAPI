package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Animal;
import app.service.AnimalService;

@RestController
@RequestMapping("/api/animal")
@CrossOrigin("*")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Animal>> findAll() {
        List<Animal> lista = this.animalService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/findByNome")
    public ResponseEntity<List<Animal>> findByNome(@RequestParam("nome") String nome) {
        List<Animal> lista = this.animalService.findByNome(nome);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<Animal> findById(@PathVariable("id") long id) {
        Animal animal = this.animalService.findById(id);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        String mensagem = this.animalService.delete(id);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Animal animal) {
        String mensagem = this.animalService.save(animal);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Animal animal, @PathVariable("id") long id) {
        String mensagem = this.animalService.update(id, animal);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
}
