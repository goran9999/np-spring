package rs.fon.silab.service;

import java.util.List;

import rs.fon.silab.dto.CourseDto;


public interface CourseService {

	CourseDto saveCourse(CourseDto course);
	List<CourseDto>getAllCourses();
	CourseDto getOneCourse(Long courseId);
	
}