
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

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

	@Autowired
	StudentServiceImpl studentServiceImpl;
	
	@GetMapping("get")
	StudentDto getStudent(@RequestParam String id) {
		return this.studentServiceImpl.getStudent(Long.valueOf(id));
	}
	
	@GetMapping("all")
	List<StudentDto>getAllStudents(){
		return this.studentServiceImpl.getStudents();
	}
	
	@PostMapping("save")
	StudentDto saveStudent(@RequestBody StudentDto studentDto) {
		return this.studentServiceImpl.saveStudent(studentDto);
	}
	
	@PatchMapping("update")
	StudentDto updateStudent(@RequestBody StudentDto studentDto) {
		return this.studentServiceImpl.updateStudent(studentDto);
	}
	
	@DeleteMapping("delete")
	boolean deleteStudent(@RequestParam String id) {
		return this.studentServiceImpl.deleteStudent(Long.valueOf(id));
	}
	
	
}