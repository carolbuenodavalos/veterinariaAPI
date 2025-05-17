package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
	
    List<Vacina> findByNomeIgnoreCaseContaining(String nome); // Filtra por nome (ignorando maiúsculas/minúsculas)
    List<Vacina> findByLote(String lote); // Filtra por lote

}