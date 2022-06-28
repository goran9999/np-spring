package rs.fon.silab.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.RETURNS_DEFAULTS;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.web.server.ResponseStatusException;

import rs.fon.silab.converter.CourseConverter;
import rs.fon.silab.dto.CourseDto;
import rs.fon.silab.model.Course;
import rs.fon.silab.model.Group;
import rs.fon.silab.repository.CourseRepository;
import rs.fon.silab.service.impl.CourseServiceImpl;
import rs.fon.silab.util.Semester;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)

public class CourseServiceImplTest {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseConverter courseConverter;

	@Autowired
	private CourseServiceImpl courseServiceImpl;

	@Test
	void shouldSaveEntityToDatabase() {
		Calendar c = Calendar.getInstance();
		c.set(2023, 2, 3);
		CourseDto courseDto = new CourseDto(null, "test", Semester.summer,c.getTime(), c.getTime(),
				"begginner", 20, "test:png://");
		CourseDto savedCourse = this.courseServiceImpl.saveCourse(courseDto);
		System.out.println(savedCourse.getId());
		assertEquals(savedCourse.getId(), Long.valueOf(1));
	}

	@Test
	void shouldLoadEmptyListOfEntities() {

		assertThrows(ResponseStatusException.class, () -> {
			this.courseServiceImpl.getAllCourses();
		}, "No saved courses!");
	}

	void addEntitiesToDatabase() {
		Course c1 = new Course(Long.valueOf(1), "test1", Semester.summer, new Date(), new Date(), "medium", 0, "test");
		Course c2 = new Course(Long.valueOf(2), "test2", Semester.summer, new Date(), new Date(), "medium", 0, "test");
		Course c3 = new Course(Long.valueOf(3), "test3", Semester.summer, new Date(), new Date(), "medium", 0, "test");
		this.courseRepository.save(c1);
		this.courseRepository.save(c2);
		this.courseRepository.save(c3);
	}

	@Test
	void shouldLoadListOfEntities() {
		addEntitiesToDatabase();
		List<CourseDto> courses = this.courseServiceImpl.getAllCourses();
		System.out.println(courses.size());
		assertEquals(courses.size(), 3);
	}

	@Test
	void shouldLoadEntity() {
		addEntitiesToDatabase();
		CourseDto c = this.courseServiceImpl.getOneCourse(Long.valueOf(1));
		assertEquals(c.getCourseName(), "test1");
	}

}