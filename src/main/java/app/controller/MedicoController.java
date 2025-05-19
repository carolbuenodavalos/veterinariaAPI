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

import app.entity.Medico;
import app.service.MedicoService;

@RestController
@RequestMapping("/api/medico")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin("*")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Medico>> findAll() {
        List<Medico> lista = this.medicoService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/findByNome")
    public ResponseEntity<List<Medico>> findByNome(@RequestParam("nome") String nome) {
        List<Medico> medicos = this.medicoService.findByNome(nome);
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/findByEspecialidade")
    public ResponseEntity<List<Medico>> findByEspecialidade(@RequestParam("especialidade") String especialidade) {
        List<Medico> medicos = this.medicoService.findByEspecialidade(especialidade);
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Medico> findById(@PathVariable("id") long id) {
        Medico medico = this.medicoService.findById(id);
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        String mensagem = this.medicoService.delete(id);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Medico medico) {
        String mensagem = this.medicoService.save(medico);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Medico medico, @PathVariable("id") long id) {
        String mensagem = this.medicoService.update(id, medico);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
}