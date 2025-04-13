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
import app.entity.Vacina;
import app.service.VacinaService;

@RestController
@RequestMapping("api/vacina")
@CrossOrigin("*")
public class VacinaController {

    @Autowired
    private VacinaService vacinaService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Vacina>> findAll() {
        List<Vacina> lista = this.vacinaService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/findByNome")
    public ResponseEntity<List<Vacina>> findByNome(@RequestParam("nome") String nome) {
        List<Vacina> vacinas = this.vacinaService.findByNome(nome);
        return new ResponseEntity<>(vacinas, HttpStatus.OK);
    }

    @GetMapping("/findByLote")
    public ResponseEntity<List<Vacina>> findByLote(@RequestParam("lote") String lote) {
        List<Vacina> vacinas = this.vacinaService.findByLote(lote);
        return new ResponseEntity<>(vacinas, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Vacina> findById(@PathVariable("id") long id) {
        Vacina vacina = this.vacinaService.findById(id);
        return new ResponseEntity<>(vacina, HttpStatus.OK);
    }

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){

			String mensagem = this.vacinaService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
	}
	
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Vacina vacina) {
        String mensagem = this.vacinaService.save(vacina);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Vacina vacina, @PathVariable("id") long id) {
        String mensagem = this.vacinaService.update(id, vacina);
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

}