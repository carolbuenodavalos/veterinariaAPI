package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByNomeIgnoreCaseContaining(String nome); // Filtra por nome (ignorando maiúsculas/minúsculas)
    List<Animal> findByEspecie(String especie); // Filtra por espécie

    @Query("SELECT a FROM Animal a WHERE a.idade > :idade")
    List<Animal> findByAgeGreaterThan(@Param("idade") String idade); // Filtra animais com idade maior que o valor informado
}