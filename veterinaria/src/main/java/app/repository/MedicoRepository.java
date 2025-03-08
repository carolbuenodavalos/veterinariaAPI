package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}