
package rs.fon.silab.service;

import java.util.List;

import rs.fon.silab.dto.StudentDto;

public interface StudentService {
	StudentDto saveStudent(StudentDto studentDto);
	boolean deleteStudent(Long id);
	List<StudentDto>getStudents();
	StudentDto updateStudent(StudentDto studentDto);
	StudentDto getStudent(Long id);
}