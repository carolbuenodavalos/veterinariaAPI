package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Consulta;
import app.entity.Medico;
import app.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;
	
	
	public String save(Medico consulta) {
		this.medicoRepository.save(consulta); 
		return "Salvo com sucesso";
	}

	public String delete(long id) {
		this.medicoRepository.deleteById(id);
		return "Deletado com sucesso";
	}
	

	public String update(long id, Medico medico) {
		medico.setId(id);
		this.medicoRepository.save(medico); 
		return "atualizado com sucesso";
	}
	
	public Medico findById(long id) { 
		return this.medicoRepository.findById(id).get();
	}
	
	
    
    public List<Medico> findAll() {
		return medicoRepository.findAll();
    }

    public List<Medico> findByNome(String nome) {
        return medicoRepository.findByNomeIgnoreCaseContaining(nome);
    }

    public List<Medico> findByEspecialidade(String especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }
    
}
