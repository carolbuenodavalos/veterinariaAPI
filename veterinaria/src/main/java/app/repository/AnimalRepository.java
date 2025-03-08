package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}