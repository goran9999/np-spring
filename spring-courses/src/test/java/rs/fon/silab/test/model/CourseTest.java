package rs.fon.silab.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import rs.fon.silab.model.Course;
import rs.fon.silab.util.Semester;

@SpringBootTest
public class CourseTest {

	Course c;
	
	@BeforeEach
	void createInstance() {
		c=new Course();
	}
	
	@AfterEach
	void teardown() {
		c=null;
	}
	
	@Test
	void testSetId() {
		c.setId(Long.valueOf(1));
		assertEquals(c.getId(), Long.valueOf(1));
	}
	
	@Test
	void shouldThrowExceptionForNegativeId() {
		assertThrows(IllegalArgumentException.class, ()->{c.setId(Long.valueOf(-23));});
	}
	
	@Test
	void shouldThrowNullPointerForNullId() {
		assertThrows(NullPointerException.class, ()->{c.setId(null);});
	}
	
	@Test
	void testSetCourseName(){
		c.setCourseName("test");
		assertEquals(c.getCourseName(), "test");
	}
	
	@Test
	void shouldThrowException() {
		assertThrows(IllegalArgumentException.class, ()->{c.setCourseName(null);});
	}
	
	@Test
	void shouldSetCurrentDate() {
		c.setStartDate(new Date());
		assertEquals(c.getStartDate().getDay(), new Date().getDay());
	}
	
	@Test
	void shouldNotAllowDateInPast() {
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 2, 3);
		Date d=cal.getTime();
		assertThrows(IllegalArgumentException.class, ()->{c.setEndDate(d);});
		
		
	}
	
	@Test
	void shouldSetGroupsCount() {
		c.setGroupsCount(23);
		assertEquals(c.getGroupsCount(), 23);
	}
	
	@Test
	void shouldThrowNullPointerException() {
		assertThrows(NullPointerException.class, ()->{c.setGroupsCount(0);});
	}
	
	@Test
	void shouldNotAllowNegativeGroupsCount() {
		
		assertThrows(IllegalArgumentException.class, ()->{c.setGroupsCount(-23);});
	}
	
	@Test
	void shouldNotAllowNullSemester() {
		assertThrows(NullPointerException.class, ()->{c.setSemester(null);});
	}
	
	@Test
	void setSemester() {
		c.setSemester(Semester.summer);
		assertEquals(c.getSemester(), Semester.summer);
	}
	
	@ParameterizedTest
	@CsvSource({
		"Course1,winter,23,Course1,winter,23,true",
		"Course2,summer,21,Course2,winter,24,false"
	})
	void testEqualsMethod(String courseName1,Semester sem1,int groupsCount1,String courseName2,Semester sem2,int groupsCount2,boolean equals) {
		c.setCourseName(courseName1);
		c.setSemester(sem1);
		c.setGroupsCount(groupsCount1);
		
		Course c2=new Course();
		c2.setCourseName(courseName2);
		c2.setSemester(sem2);
		c2.setGroupsCount(groupsCount2);
		
		assertEquals(equals, c.equals(c2));
	}
	
	@Test
	void testToString() {
		c.setCourseName("course1");
		c.setGroupsCount(34);
		
		String s=c.toString();
		
		assertTrue(s.contains("course1"));
		assertTrue(s.contains("34"));
		
	}
}