package rs.fon.silab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.fon.silab.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}