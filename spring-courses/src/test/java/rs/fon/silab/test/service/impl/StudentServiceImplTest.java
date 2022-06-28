package rs.fon.silab.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import rs.fon.silab.converter.StudentConverter;
import rs.fon.silab.dto.StudentDto;
import rs.fon.silab.model.Student;
import rs.fon.silab.repository.StudentRepository;
import rs.fon.silab.service.impl.StudentServiceImpl;
import rs.fon.silab.util.StudentStatus;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY )
public class StudentServiceImplTest {

	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private StudentConverter converter;
	
	@Autowired
	private StudentServiceImpl impl;
	
	
	@BeforeEach
	void addStudentsToDatabase() {
		Student s1=new Student(Long.valueOf(1), "testName1", "testLastName1", new Date(), StudentStatus.active);
		Student s2=new Student(Long.valueOf(2), "testName2", "testLastName2", new Date(), StudentStatus.finished);
		Student s3=new Student(Long.valueOf(3), "testName3", "testLastName3", new Date(), StudentStatus.paused);
		
		this.repository.save(s1);
		this.repository.save(s2);
		this.repository.save(s3);
	}
	
	@Test
	void shouldConvertDtoToEntity() {
		StudentDto s=new StudentDto(null, "test", "test", new Date(), StudentStatus.active,new ArrayList<>());
		Student student=this.converter.toEntity(s);
		assertEquals(student.getFirstName(), "test");
	}
	
	@Test
	void shouldSaveStudentToDatabase() {
		StudentDto s3=new StudentDto(null, "test4", "test4", new Date(), StudentStatus.active, new ArrayList<>());
		StudentDto savedStudentDto=this.impl.saveStudent(s3);
		assertEquals(savedStudentDto.getId(), Long.valueOf(4));
	}
	
	@Test
	void shouldReturnStudent() {
		StudentDto s=this.impl.getStudent(Long.valueOf(1));
		assertEquals(s.getFirstName(), "testName1");
	}
	
	@Test
	void shouldReturnAllStudents() {
		List<StudentDto>students=this.impl.getStudents();
		assertEquals(students.size(), 3);
	}
	
	@Test
	void shouldUpdateStudent() {
		StudentDto s=this.impl.getStudent(Long.valueOf(1));
		String newName="updatedName";
		s.setFirstName(newName);
		StudentDto updated=this.impl.updateStudent(s);
		assertEquals(updated.getFirstName(), newName);
	}
	
	@Test
	void shouldDeleteStudnet() {
		assertTrue(this.impl.deleteStudent(Long.valueOf(1)));
	}
	
	@Test
	void shouldNotDeleteStudent() {
		assertThrows(NoSuchElementException.class, ()->{this.impl.deleteStudent(Long.valueOf(23));});
	}
}