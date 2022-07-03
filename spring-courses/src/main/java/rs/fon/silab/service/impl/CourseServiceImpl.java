package rs.fon.silab.service.impl;


import java.util.LinkedList;
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

/**
 * @author goran
 * 
 * Service implementation which contains business logic for course entity
 */
@Service
public class CourseServiceImpl implements CourseService {

	/**
	 * Repository interface used for database interaction
	 */
	private CourseRepository courseRepository;
	/**
	 * Class used to convert course and coursedto
	 */
	private CourseConverter courseConverter;
	
	@Autowired
	public CourseServiceImpl(final CourseRepository repository,final CourseConverter courseConverter) {
		this.courseRepository=repository;
		this.courseConverter=courseConverter;
	}

	/**
	 * Service class used to save courses in .json file
	 */
	@Autowired
	private SaveCoursesToFileImpl coursesToFileImpl;




	/**
	 * @return List of all courses found in database
	 * @throws - {@link ResponseStatusException} - if there is no saved courses in db
	 */
	@Override
	public List<CourseDto> getAllCourses() {
		try {
			List<Course> courses = this.courseRepository.findAll();
			if (courses.size() > 0) {
				List<CourseDto> foundCourses = new LinkedList<>();
				courses.forEach((course) -> {
					CourseDto courseDto = this.courseConverter.toDto(course);
					foundCourses.add(courseDto);
				});
				this.coursesToFileImpl.saveCoursesToFile(foundCourses);
				return foundCourses;
			} else {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No saved courses!");
			}
		} catch (Exception e) {
			System.out.println(e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problem with loading courses!", e);
		}
	}

	/**
	 * @param courseId - id of course we want to fetch from database
	 * @return CourseDto - CourseDto object with specific given id
	 * @throws  {@link ResponseStatusException} if course is not found in database or there is problem with loading course
	 */
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

	/**
	 * @param CourseDto - CourseDto object we want to save in database
	 * @return CourseDto - CourseDto object which represents newly saved entity in db
	 * @throws  {@link ResponseStatusException} - if there is problem with saving course in db
	 * 
	 */
	@Override
	public CourseDto saveCourse(CourseDto course) {

		try {
			Course newCourse = this.courseConverter.toEntity(course);
			Course savedCourse = this.courseRepository.save(newCourse);
			return this.courseConverter.toDto(savedCourse);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage() );
		}

	}

}