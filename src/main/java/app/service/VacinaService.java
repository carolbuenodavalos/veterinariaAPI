package app.service;

import app.entity.Vacina;
import app.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository vacinaRepository;

    public String save(Vacina vacina) {
        vacinaRepository.save(vacina);
        return "Nova vacina salva com sucesso";
    }

    public String update(long id, Vacina vacina) {
        Vacina existente = vacinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada para atualizar"));
        vacina.setId(id);
        vacinaRepository.save(vacina);
        return "atualizado com sucesso";
    }

    public String delete(long id) {
        Vacina existente = vacinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada para deletar"));
        vacinaRepository.deleteById(id);
        return "Vacina deletada com sucesso";
    }

    public Vacina findById(long id) {
        return vacinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada com ID: " + id));
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

