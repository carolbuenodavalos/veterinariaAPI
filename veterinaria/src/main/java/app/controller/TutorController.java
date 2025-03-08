package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Tutor;
import app.service.TutorService;

@RestController
@RequestMapping("api/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Tutor tutor) {
        try {
            String mensagem = this.tutorService.save(tutor);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Erro interno no servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        try {
            String mensagem = this.tutorService.delete(id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Tutor> findById(@PathVariable long id) {
        try {
            Tutor tutor = this.tutorService.findById(id);
            return new ResponseEntity<>(tutor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Tutor>> findAll() {
        try {
            List<Tutor> lista = this.tutorService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/findByNome")
    public ResponseEntity<List<Tutor>> findByNome(@RequestParam String nome) {
        try {
            List<Tutor> tutores = this.tutorService.findByNome(nome);
            return new ResponseEntity<>(tutores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByCpf")
    public ResponseEntity<List<Tutor>> findByCpf(@RequestParam String cpf) {
        try {
            List<Tutor> tutores = this.tutorService.findByCpf(cpf);
            return new ResponseEntity<>(tutores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}