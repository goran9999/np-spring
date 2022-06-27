package rs.fon.silab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.fon.silab.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{

}