package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

}