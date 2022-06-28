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
import rs.fon.silab.util.DegreeLevel;

public class ProfessorTest {

	Professor p;
	
	@BeforeEach
	void createProfessor() {
		p=new Professor();
	}
	
	@AfterEach
	void teardown() {
		p=null;
	}
	
	@Test
	void testSetId() {
		p.setId(Long.valueOf(1));
		assertEquals(p.getId(), Long.valueOf(1));
	}

	@Test
	void shouldThrowExceptionForNegativeId() {
		assertThrows(IllegalArgumentException.class, () -> {
			p.setId(Long.valueOf(-23));
		});
	}

	@Test
	void shouldThrowNullPointerForNullId() {
		assertThrows(NullPointerException.class, () -> {
			p.setId(null);
		});
	}

	@Test
	void shouldSetFirstName() {
		p.setFirstName("group1");
		assertEquals(p.getFirstName(), "group1");
	}

	@Test
	void shouldNotAllowEmptyStringAsFirstName() {
		assertThrows(IllegalArgumentException.class, () -> {
			p.setFirstName("");
		});
	}

	@Test
	void shouldNotAllowNullAsFirstName() {
		assertThrows(NullPointerException.class, () -> {
			p.setFirstName(null);
		});
	}
	@Test
	void shouldSetLastName() {
		p.setLastName("group1");
		assertEquals(p.getLastName(), "group1");
	}

	@Test
	void shouldNotAllowEmptyStringAsLastName() {
		assertThrows(IllegalArgumentException.class, () -> {
			p.setLastName("");
		});
	}

	@Test
	void shouldNotAllowNullAsLasttName() {
		assertThrows(NullPointerException.class, () -> {
			p.setLastName(null);
		});
	}
	
	@Test
	void shouldSetBirthDate() {
		Calendar cal=Calendar.getInstance();
		cal.set(1995, 2, 3);
		p.setBithDate(cal.getTime());
		assertEquals(p.getBithDate(), cal.getTime());
	}
	
	@Test
	void shouldNotAllowBirthDateInFuture() {
		Calendar cal=Calendar.getInstance();
		cal.set(2025, 2, 3);
		assertThrows(IllegalArgumentException.class, ()->{p.setBithDate(cal.getTime());});
	}
	
	@Test
	void shouldSetDegreeLevel() {
		p.setDegreeLevel(DegreeLevel.ix);
		assertEquals(p.getDegreeLevel(), DegreeLevel.ix);
	}
	
	@Test
	void shouldNotAllowNullAsDegreeLevel() {
		assertThrows(NullPointerException.class, ()->{p.setDegreeLevel(null);});
	}
	
	@Test
	void testToString() {
		p.setFirstName("firstName1");
		p.setLastName("lastName1");
		String s=p.toString();
		assertTrue(s.contains("firstName1"));
		assertTrue(s.contains("lastName1"));
	}
	
	@ParameterizedTest
	@CsvSource({
		"1,firstName1,lastName1,ix,1,firstName1,lastName1,ix,true",
		"1,firstName1,lastName1,ix,2,firstName1,lastName1,ix,false,",
		"1,firstName1,lastName1,ix,1,fistName2,lastName1,ix,false",
		"1,fistName1,lastName2,ix,1,firstName1,lastName1,ix,false",
		"1,fistName1,lastName1,ix,1,fistName1,lastName1,vii,false"
	})
	void testEqualsMethod(Long id1,String name1,String surname1,DegreeLevel dl1,Long id2,String name2,String surname2,DegreeLevel dl2,boolean equals) {
		p.setId(id1);
		p.setFirstName(name1);
		p.setLastName(surname1);
		p.setDegreeLevel(dl1);
		Professor p2=new Professor();
		p2.setId(id2);
		p2.setFirstName(name2);
		p2.setLastName(surname2);
		p2.setDegreeLevel(dl2);
		assertEquals(p.equals(p2), equals);
	}
}
