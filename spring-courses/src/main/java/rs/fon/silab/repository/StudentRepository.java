package rs.fon.silab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.fon.silab.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}