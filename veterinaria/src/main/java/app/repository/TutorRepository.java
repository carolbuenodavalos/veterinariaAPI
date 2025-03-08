package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    List<Tutor> findByNomeIgnoreCaseContaining(String nome); // Filtra por nome (ignorando maiúsculas/minúsculas)
    List<Tutor> findByCpf(String cpf); // Filtra por CPF

    @Query("SELECT t FROM Tutor t WHERE SIZE(t.animais) > :quantidade")
    List<Tutor> findByQuantidadeAnimaisGreaterThan(@Param("quantidade") int quantidade);
}