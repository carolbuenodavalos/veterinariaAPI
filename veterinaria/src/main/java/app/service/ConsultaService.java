package app.service;

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
		return "Aluno deletado com sucesso";
	}
	
	public Consulta findById(long id) { 
		return this.consultaRepository.findById(id).get();
	}
	
	
    
    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

}
