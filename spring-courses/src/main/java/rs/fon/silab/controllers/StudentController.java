
package rs.fon.silab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.silab.dto.StudentDto;
import rs.fon.silab.service.impl.StudentServiceImpl;

/**
 * 
 * @author goran
 * Controller used to accept requests that
 * are related to Student entity
 */
@RestController
@RequestMapping("api/v1/student")
public class StudentController {

	@Autowired
	StudentServiceImpl studentServiceImpl;
	
	/**
	 * 
	 * @param id - Value of id of specific student we want to load from database
	 * @return - StudentDto representing course with given id
	 */
	@GetMapping("get")
	StudentDto getStudent(@RequestParam String id) {
		return this.studentServiceImpl.getStudent(Long.valueOf(id));
	}
	
	/**
	 * 
	 * @return - List of all professors found in database
	 */
	@GetMapping("all")
	List<StudentDto>getAllStudents(){
		return this.studentServiceImpl.getStudents();
	}
	
	/**
	 * 
	 * @param student - StudentDto that we want to save in database
	 * @return - StudentDto representing saved entity in database
	 */
	@PostMapping("save")
	StudentDto saveStudent(@RequestBody StudentDto studentDto) {
		return this.studentServiceImpl.saveStudent(studentDto);
	}
	
	/**
	 * 
	 * @param studentDto-StudentDto having id of existing professor entity from
	 * database whose data should be updated
	 * @return - Updated old student data
	 */
	@PatchMapping("update")
	StudentDto updateStudent(@RequestBody StudentDto studentDto) {
		return this.studentServiceImpl.updateStudent(studentDto);
	}
	
	/**
	 * 
	 * @param id - Value of id of student we want to delete
	 * @return
	 * <ul>
	 * <li>true-if student is successfully deleted from database</li>
	 * <li>false-if deleting student failed</li>
	 * </ul>
	 */
	@DeleteMapping("delete")
	boolean deleteStudent(@RequestParam String id) {
		return this.studentServiceImpl.deleteStudent(Long.valueOf(id));
	}
	
	
}