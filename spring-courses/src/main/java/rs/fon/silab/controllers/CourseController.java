package rs.fon.silab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.silab.dto.CourseDto;
import rs.fon.silab.model.Course;
import rs.fon.silab.service.impl.CourseServiceImpl;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

	@Autowired
	CourseServiceImpl courseService;

	@GetMapping("all")
	List<CourseDto> findAllCourses(){
		System.out.println("BBB");
		return this.courseService.getAllCourses();
	}
	
	@PostMapping("save")
	CourseDto saveCourse(@RequestBody CourseDto course) {
		return this.courseService.saveCourse(course);
	}
	
	@GetMapping("get")
	CourseDto getCourseById(@RequestParam("id") String id) {
		return this.courseService.getOneCourse(Long.valueOf(id));
	}
}