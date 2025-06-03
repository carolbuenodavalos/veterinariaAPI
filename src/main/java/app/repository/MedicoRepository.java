package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    List<Medico> findByNomeIgnoreCaseContaining(String nome); // Filtra por nome (ignorando maiúsculas/minúsculas)
    List<Medico> findByEspecialidade(String especialidade); // Filtra por especialidade
    
    Medico findByUsuarioId(Long usuarioId);
}