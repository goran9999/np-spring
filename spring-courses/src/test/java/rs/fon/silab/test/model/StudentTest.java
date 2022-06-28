package rs.fon.silab.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import rs.fon.silab.model.Professor;
import rs.fon.silab.model.Student;
import rs.fon.silab.util.DegreeLevel;
import rs.fon.silab.util.StudentStatus;

public class StudentTest {
	
	Student s;

	@BeforeEach
	void createProfessor() {
		s=new Student();
	}
	
	@AfterEach
	void teardown() {
		s=null;
	}
	@Test
	void testSetId() {
		s.setId(Long.valueOf(1));
		assertEquals(s.getId(), Long.valueOf(1));
	}

	@Test
	void shouldThrowExceptionForNegativeId() {
		assertThrows(IllegalArgumentException.class, () -> {
			s.setId(Long.valueOf(-23));
		});
	}

	@Test
	void shouldThrowNullPointerForNullId() {
		assertThrows(NullPointerException.class, () -> {
			s.setId(null);
		});
	}

	@Test
	void shouldSetFirstName() {
		s.setFirstName("group1");
		assertEquals(s.getFirstName(), "group1");
	}

	@Test
	void shouldNotAllowEmptyStringAsFirstName() {
		assertThrows(IllegalArgumentException.class, () -> {
			s.setFirstName("");
		});
	}

	@Test
	void shouldNotAllowNullAsFirstName() {
		assertThrows(NullPointerException.class, () -> {
			s.setFirstName(null);
		});
	}
	@Test
	void shouldSetLastName() {
		s.setLastName("group1");
		assertEquals(s.getLastName(), "group1");
	}

	@Test
	void shouldNotAllowEmptyStringAsLastName() {
		assertThrows(IllegalArgumentException.class, () -> {
			s.setLastName("");
		});
	}

	@Test
	void shouldNotAllowNullAsLasttName() {
		assertThrows(NullPointerException.class, () -> {
			s.setLastName(null);
		});
	}
	
	@Test
	void shouldSetBirthDate() {
		Calendar cal=Calendar.getInstance();
		cal.set(1995, 2, 3);
		s.setBirthDate(cal.getTime());
		assertEquals(s.getBirthDate(), cal.getTime());
	}
	
	@Test
	void shouldNotAllowBirthDateInFuture() {
		Calendar cal=Calendar.getInstance();
		cal.set(2025, 2, 3);
		assertThrows(IllegalArgumentException.class, ()->{s.setBirthDate(cal.getTime());});
	}
	
	@Test
	void shouldSetStudentStatus() {
		s.setStudentStatus(StudentStatus.active);
		assertEquals(s.getStudentStatus(), StudentStatus.active);
	}
	
	@Test
	void shouldNotAllowNullAsStudentStatus() {
		assertThrows(NullPointerException.class, ()->{s.setStudentStatus(null);});
	}
	
	@Test
	void testToString() {
		s.setFirstName("firstName1");
		s.setLastName("lastName1");
		String s1=s.toString();
		assertTrue(s1.contains("firstName1"));
		assertTrue(s1.contains("lastName1"));
	}
	
	@ParameterizedTest
	@CsvSource({
		"1,firstName1,lastName1,active,1,firstName1,lastName1,active,true",
		"1,firstName1,lastName1,paused,2,firstName1,lastName1,paused,false,",
		"1,firstName1,lastName1,finished,1,fistName2,lastName1,finished,false",
		"1,fistName1,lastName2,active,1,firstName1,lastName1,active,false",
		"1,fistName1,lastName1,active,1,fistName1,lastName1,finished,false"
	})
	void testEqualsMethod(Long id1,String name1,String surname1,StudentStatus ss1,Long id2,String name2,String surname2,StudentStatus ss2,boolean equals) {
		s.setId(id1);
		s.setFirstName(name1);
		s.setLastName(surname1);
		s.setStudentStatus(ss1);
		Student s2=new Student();
		s2.setId(id2);
		s2.setFirstName(name2);
		s2.setLastName(surname2);
		s2.setStudentStatus(ss2);
		assertEquals(s.equals(s2), equals);
	}
	
}
