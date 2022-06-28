package rs.fon.silab.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import rs.fon.silab.model.Area;

public class AreaTest {

	Area a;

	@BeforeEach
	void createArea() {
		a = new Area();
	}

	@AfterEach
	void teardown() {
		a = null;
	}

	@Test
	void testSetId() {
		a.setId(Long.valueOf(1));
		assertEquals(a.getId(), Long.valueOf(1));
	}

	@Test
	void shouldThrowExceptionForNegativeId() {
		assertThrows(IllegalArgumentException.class, () -> {
			a.setId(Long.valueOf(-23));
		});
	}

	@Test
	void shouldThrowNullPointerForNullId() {
		assertThrows(NullPointerException.class, () -> {
			a.setId(null);
		});
	}

	@Test
	void shouldSetAreaName() {
		a.setAreaName("group1");
		assertEquals(a.getAreaName(), "group1");
	}

	@Test
	void shouldNotAllowEmptyStringAsGroupName() {
		assertThrows(IllegalArgumentException.class, () -> {
			a.setAreaName("");
		});
	}

	@Test
	void shouldNotAllowNullAsGroupName() {
		assertThrows(NullPointerException.class, () -> {
			a.setAreaName(null);
		});
	}

	@Test
	void shouldSetClassesCount() {
		a.setClassesCount(25);
		assertEquals(a.getClassesCount(), 25);
	}

	@Test
	void shouldThrowExceptionForNegativeClassesCount() {
		assertThrows(IllegalArgumentException.class, () -> {
			a.setClassesCount(-23);
		});
	}

	@Test
	void testToStringMethod() {
		a.setAreaName("area1");
		String s = a.toString();
		assertTrue(s.contains("area1"));
	}

	@ParameterizedTest
	@CsvSource({ "1,Area1,23,1,Area1,23,true", 
		"2,Area1,23,2,Area2,23,false",
		"3,Area3,25,3,Area3,21,false" })
	void testEqualsMethod(Long id1,String areaName1,int classesCount1,Long id2,String areaName2,int classesCount2,boolean equals) {
		a.setId(id1);
		a.setAreaName(areaName1);
		a.setClassesCount(classesCount1);
		Area area2=new Area();
		area2.setId(id2);
		area2.setAreaName(areaName2);
		area2.setClassesCount(classesCount2);
		assertEquals(a.equals(area2), equals);
	}

}
