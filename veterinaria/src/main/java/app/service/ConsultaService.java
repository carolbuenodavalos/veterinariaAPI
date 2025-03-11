package app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Consulta;
import app.repository.ConsultaRepository;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	
    public String save(Consulta consulta) {
        // Regra de negócio
        if ("EMERGÊNCIA".equalsIgnoreCase(consulta.getTipoConsulta())) {
            consulta.setStatusUrgencia("URGENTE");
        } else {
            consulta.setStatusUrgencia("NORMAL");
        }
        this.consultaRepository.save(consulta);
        return "Consulta salva com sucesso";
    }

	public String delete(long id) {
		this.consultaRepository.deleteById(id);
		return "deletado com sucesso";
	}
	
	public Consulta findById(long id) { 
		return this.consultaRepository.findById(id).get();
	}
	

	public String update(long id, Consulta consulta) {
		consulta.setId(id);
		this.consultaRepository.save(consulta); 
		return "atualizado com sucesso";
	}
	
	
    
    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public List<Consulta> findByDataHora(LocalDateTime dataHora) {
        return consultaRepository.findByDataHora(dataHora);
    }

    public List<Consulta> findByStatus(String status) {
        return consultaRepository.findByStatus(status);
    }

    public List<Consulta> findByAnimalId(Long animalId) {
        return consultaRepository.findByAnimalId(animalId);
    }
    
    
}
