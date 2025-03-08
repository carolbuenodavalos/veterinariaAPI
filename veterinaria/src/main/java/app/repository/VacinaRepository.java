package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {

}