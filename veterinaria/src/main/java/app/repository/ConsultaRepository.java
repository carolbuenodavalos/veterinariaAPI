package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}