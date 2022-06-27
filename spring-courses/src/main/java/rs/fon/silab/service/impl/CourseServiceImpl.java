package rs.fon.silab.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rs.fon.silab.converter.CourseConverter;
import rs.fon.silab.dto.CourseDto;
import rs.fon.silab.model.Course;
import rs.fon.silab.repository.CourseRepository;
import rs.fon.silab.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;
	private CourseConverter courseConverter;
	
	@Autowired
	public CourseServiceImpl(final CourseRepository repository,final CourseConverter courseConverter) {
		this.courseRepository=repository;
		this.courseConverter=courseConverter;
	}




	@Override
	public List<CourseDto> getAllCourses() {
		try {
			List<Course> courses = this.courseRepository.findAll();
			if (courses.size() > 0) {
				List<CourseDto> foundCourses = new ArrayList<>();
				courses.forEach((course) -> {
					CourseDto courseDto = this.courseConverter.toDto(course);
					foundCourses.add(courseDto);
				});
				
				return foundCourses;
			} else {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No saved courses!");
			}
		} catch (Exception e) {
			System.out.println(e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problem with loading courses!", e);
		}
	}

	@Override
	public CourseDto getOneCourse(Long courseId) {
		try {
			Optional<Course> course = this.courseRepository.findById(courseId);
			if (course.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course with this id does not exist");
			}
			Course foundCourse = course.get();
			return this.courseConverter.toDto(foundCourse);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problem with loading course!", e);
		}
	}

	@Override
	public CourseDto saveCourse(CourseDto course) {

		try {
			Course newCourse = this.courseConverter.toEntity(course);
			Course savedCourse = this.courseRepository.save(newCourse);
			return this.courseConverter.toDto(savedCourse);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problem with saving course!", e);
		}

	}

}