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

import app.entity.Medico;
import app.service.MedicoService;

@RestController
@RequestMapping("api/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Medico medico) {
        try {
            String mensagem = this.medicoService.save(medico);
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
            String mensagem = this.medicoService.delete(id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Medico> findById(@PathVariable long id) {
        try {
            Medico medico = this.medicoService.findById(id);
            return new ResponseEntity<>(medico, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Medico>> findAll() {
        try {
            List<Medico> lista = this.medicoService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/findByNome")
    public ResponseEntity<List<Medico>> findByNome(@RequestParam String nome) {
        try {
            List<Medico> medicos = this.medicoService.findByNome(nome);
            return new ResponseEntity<>(medicos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByEspecialidade")
    public ResponseEntity<List<Medico>> findByEspecialidade(@RequestParam String especialidade) {
        try {
            List<Medico> medicos = this.medicoService.findByEspecialidade(especialidade);
            return new ResponseEntity<>(medicos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}