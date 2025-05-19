package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import app.entity.Tutor;
import app.service.TutorService;

@RestController
@RequestMapping("api/tutor")
@CrossOrigin("*")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    
    @GetMapping("/findAll")
    public ResponseEntity<List<Tutor>> findAll() {
        List<Tutor> lista = this.tutorService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    
    @GetMapping("/findByNome")
    public ResponseEntity<List<Tutor>> findByNome(@RequestParam("nome") String nome) {
        List<Tutor> tutores = this.tutorService.findByNome(nome);
        return new ResponseEntity<>(tutores, HttpStatus.OK);
    }

    @GetMapping("/findByCpf")
    public ResponseEntity<List<Tutor>> findByCpf(@RequestParam("cpf") String cpf) {
        List<Tutor> tutores = this.tutorService.findByCpf(cpf);
        return new ResponseEntity<>(tutores, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Tutor> findById(@PathVariable("id") long id) {
        Tutor tutor = this.tutorService.findById(id);
        return new ResponseEntity<>(tutor, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        String mensagem = this.tutorService.delete(id);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Tutor tutor) {
        String mensagem = this.tutorService.save(tutor);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Tutor tutor, @PathVariable("id") long id) {
        String mensagem = this.tutorService.update(id, tutor);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
}