package app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByDataHora(LocalDateTime dataHora); // Filtra consultas por data e hora exata
    List<Consulta> findByStatus(String status); // Filtra por status
    
    

    @Query("SELECT c FROM Consulta c WHERE c.animal.id = :animalId")
    List<Consulta> findByAnimalId(@Param("animalId") Long animalId);
    

     @Query("SELECT c FROM Consulta c WHERE c.medico.username = :username")
     List<Consulta> findByVeterinarioUsername(@Param("username") String username);
        
        
     List<Consulta> findByMedicoIdAndDataHoraAfterOrderByDataHoraAsc(Long medicoId, LocalDateTime dataHora);
}