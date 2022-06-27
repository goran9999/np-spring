package rs.fon.silab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.fon.silab.model.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long>{

}