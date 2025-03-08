package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Vacina;
import app.repository.VacinaRepository;

@Service
public class VacinaService {

	@Autowired
	private VacinaRepository vacinaRepository;
	
	
	public String save(Vacina vacina) {
		this.vacinaRepository.save(vacina); 
		return "Aluno salvo com sucesso";
	}

	public String delete(long id) {
		this.vacinaRepository.deleteById(id);
		return "Aluno deletado com sucesso";
	}
	
	public Vacina findById(long id) { 
		return this.vacinaRepository.findById(id).get();
	}
	
	
    
    public List<Vacina> findAll() {
        return vacinaRepository.findAll();
    }

}
