package app.controller;

import java.time.LocalDateTime;
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

import app.entity.Consulta;
import app.service.ConsultaService;

@RestController
@RequestMapping("/api/consulta")
@CrossOrigin("*")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Consulta>> findAll() {
        List<Consulta> lista = this.consultaService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/findByDataHora")
    public ResponseEntity<List<Consulta>> findByDataHora(@RequestParam LocalDateTime dataHora) {
        List<Consulta> consultas = this.consultaService.findByDataHora(dataHora);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/findByStatus")
    public ResponseEntity<List<Consulta>> findByStatus(@RequestParam String status) {
        List<Consulta> consultas = this.consultaService.findByStatus(status);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/findByAnimalId")
    public ResponseEntity<List<Consulta>> findByAnimalId(@RequestParam Long animalId) {
        List<Consulta> consultas = this.consultaService.findByAnimalId(animalId);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Consulta> findById(@PathVariable("id") long id) {
        Consulta consulta = this.consultaService.findById(id);
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        String mensagem = this.consultaService.delete(id);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Consulta consulta) {
        String mensagem = this.consultaService.save(consulta);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody Consulta consulta) {
        String mensagem = this.consultaService.update(id, consulta);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
}