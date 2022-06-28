package rs.fon.silab.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;


import rs.fon.silab.converter.ProfessorConverter;
import rs.fon.silab.dto.ProfessorDto;
import rs.fon.silab.model.Professor;
import rs.fon.silab.repository.ProfessorRepository;
import rs.fon.silab.service.impl.GroupServiceImpl;
import rs.fon.silab.service.impl.ProfessorServiceImpl;
import rs.fon.silab.util.DegreeLevel;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY )
public class ProfessorServiceImplTest {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private GroupServiceImpl groupServiceImpl;
	
	@Autowired
	private ProfessorServiceImpl professorServiceImpl;
	
	@Autowired
	private ProfessorConverter converter;
	
	
	@BeforeEach
	void addProfessorsToDatabase() {
		Professor p=new Professor(Long.valueOf(1), "testName1", "testLastName", new Date(), DegreeLevel.ix);
		Professor p2=new Professor(Long.valueOf(2), "testName", "testLastName", new Date(), DegreeLevel.ix);
		Professor p3=new Professor(Long.valueOf(3), "testName3", "testLastName", new Date(), DegreeLevel.ix);
		
		this.professorRepository.save(p);
		this.professorRepository.save(p2);
		this.professorRepository.save(p3);
		
	}
	
	@Test
	void shouldConvertDtoToEntity() {
		ProfessorDto dto=new ProfessorDto(null, "test1", "test1", new Date(), DegreeLevel.ix, new ArrayList<>());
		Professor p=this.converter.toEntity(dto);
		assertEquals(p.getFirstName(), "test1");
	}
	
	
	@Test
	void shouldSaveNewProfessorToDatabase() {
		ProfessorDto dto=new ProfessorDto(null, "test1", "test1", new Date(), DegreeLevel.ix, new ArrayList<>());
		ProfessorDto saved=this.professorServiceImpl.saveProfessor(dto);
		assertEquals(saved.getId(), 4);
	}
	
	@Test
	void shouldReturnListOfProfessors() {
		List<ProfessorDto>professors=this.professorServiceImpl.getAllProfessors();
		assertEquals(professors.size(), 3);
	}
	
	@Test
	void shouldReturnSingleProfessor() {
		ProfessorDto p=this.professorServiceImpl.getProfessor(Long.valueOf(3));
		assertEquals(p.getFirstName(), "testName3");
		
	}
	
	@Test
	void shouldUpdateProfessor() {
		ProfessorDto p=this.professorServiceImpl.getProfessor(Long.valueOf(3));
		p.setFirstName("New Name");
		ProfessorDto updated=this.professorServiceImpl.updateProfessor(p);
		assertEquals(updated.getFirstName(), "New Name");
	}
	
	@Test
	void shouldDeleteProfessor() {
		assertTrue(this.professorServiceImpl.deleteProfessor(Long.valueOf(3)));
	}
	
	@Test
	void shouldNotDeleteNonExistingProfessor() {
		assertFalse(this.professorServiceImpl.deleteProfessor(Long.valueOf(5)));
	}
	
	
	

}