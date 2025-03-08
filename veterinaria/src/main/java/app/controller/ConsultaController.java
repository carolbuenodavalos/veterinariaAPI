package app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("api/consulta")
public class ConsultaController {
	
	@Autowired
	private ConsultaService consultaService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@Validated @RequestBody Consulta consulta) {
	    try {
	        String mensagem = this.consultaService.save(consulta);
	        return new ResponseEntity<>(mensagem, HttpStatus.OK);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	        return new ResponseEntity<>("Erro interno no servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		try {
			String mensagem = this.consultaService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Deu erro!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Consulta> findById(@PathVariable long id){
		try {
			Consulta consulta = this.consultaService.findById(id);
			return new ResponseEntity<>(consulta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Consulta>> findAll(){
		try {
			List<Consulta> lista = this.consultaService.findAll();
			return new ResponseEntity<>(lista,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
    @GetMapping("/findByDataHora")
    public ResponseEntity<List<Consulta>> findByDataHora(@RequestParam LocalDateTime dataHora) {
        try {
            List<Consulta> consultas = this.consultaService.findByDataHora(dataHora);
            return new ResponseEntity<>(consultas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByStatus")
    public ResponseEntity<List<Consulta>> findByStatus(@RequestParam String status) {
        try {
            List<Consulta> consultas = this.consultaService.findByStatus(status);
            return new ResponseEntity<>(consultas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByAnimalId")
    public ResponseEntity<List<Consulta>> findByAnimalId(@RequestParam Long animalId) {
        try {
            List<Consulta> consultas = this.consultaService.findByAnimalId(animalId);
            return new ResponseEntity<>(consultas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
