package rs.fon.silab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.silab.dto.CourseDto;
import rs.fon.silab.service.impl.CourseServiceImpl;

/**
 * 
 * @author goran
 *	Controller used to accept requests that
 * are related to Course entity
 */
@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

	@Autowired
	CourseServiceImpl courseService;

	/**
	 * 
	 * @return - List of all courses found in database
	 */
	@GetMapping("all")
	List<CourseDto> findAllCourses(){
		System.out.println("BBB");
		return this.courseService.getAllCourses();
	}
	
	/**
	 * 
	 * @param course - CourseDto that we want to save in database
	 * @return - CourseDto representing saved entity in database
	 */
	@PostMapping("save")
	CourseDto saveCourse(@RequestBody CourseDto course) {
		return this.courseService.saveCourse(course);
	}
	
	/**
	 * 
	 * @param id - Value of id of specific course we want to load from database
	 * @return - CourseDto representing course with given id
	 */
	@GetMapping("get")
	CourseDto getCourseById(@RequestParam("id") String id) {
		return this.courseService.getOneCourse(Long.valueOf(id));
	}
}