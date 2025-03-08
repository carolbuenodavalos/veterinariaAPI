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

import app.entity.Vacina;
import app.service.VacinaService;

@RestController
@RequestMapping("api/vacina")
public class VacinaController {

    @Autowired
    private VacinaService vacinaService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Vacina vacina) {
        try {
            String mensagem = this.vacinaService.save(vacina);
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
            String mensagem = this.vacinaService.delete(id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Vacina> findById(@PathVariable long id) {
        try {
            Vacina vacina = this.vacinaService.findById(id);
            return new ResponseEntity<>(vacina, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Vacina>> findAll() {
        try {
            List<Vacina> lista = this.vacinaService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/findByNome")
    public ResponseEntity<List<Vacina>> findByNome(@RequestParam String nome) {
        try {
            List<Vacina> vacinas = this.vacinaService.findByNome(nome);
            return new ResponseEntity<>(vacinas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByLote")
    public ResponseEntity<List<Vacina>> findByLote(@RequestParam String lote) {
        try {
            List<Vacina> vacinas = this.vacinaService.findByLote(lote);
            return new ResponseEntity<>(vacinas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}