package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Consulta;
import app.entity.Vacina;
import app.repository.VacinaRepository;

@Service
public class VacinaService {

	@Autowired
	private VacinaRepository vacinaRepository;
	
	
	public String save(Vacina vacina) {
		this.vacinaRepository.save(vacina); 
		return "Nova vacina salva com sucesso";
	}

	public String delete(long id) {
		this.vacinaRepository.deleteById(id);
		return "Vacina deletada com sucesso";
	}
	

	public String update(long id, Vacina vacina) {
		vacina.setId(id);
		this.vacinaRepository.save(vacina); 
		return "atualizado com sucesso";
	}
	
	
	public Vacina findById(long id) { 
		return this.vacinaRepository.findById(id).get();
	}
	
	
    
    public List<Vacina> findAll() {
        return vacinaRepository.findAll();
    }
    
    public List<Vacina> findByNome(String nome) {
        return vacinaRepository.findByNomeIgnoreCaseContaining(nome);
    }

    public List<Vacina> findByLote(String lote) {
        return vacinaRepository.findByLote(lote);
    }

}
