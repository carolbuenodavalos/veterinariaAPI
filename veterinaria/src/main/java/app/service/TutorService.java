package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Tutor;
import app.repository.TutorRepository;

@Service
public class TutorService {

	@Autowired
	private TutorRepository tutorRepository;
	
	
	public String save(Tutor tutor) {
		this.tutorRepository.save(tutor); 
		return "Aluno salvo com sucesso";
	}

	public String delete(long id) {
		this.tutorRepository.deleteById(id);
		return "Aluno deletado com sucesso";
	}
	
	public Tutor findById(long id) { 
		return this.tutorRepository.findById(id).get();
	}
	
	
    
    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

}
