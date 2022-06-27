package rs.fon.silab.repository;


import org.springframework.stereotype.Repository;

import rs.fon.silab.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CourseRepository extends  JpaRepository<Course,Long> {
}