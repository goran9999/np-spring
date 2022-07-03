package rs.fon.silab.service.impl;


import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rs.fon.silab.converter.GroupConverter;
import rs.fon.silab.converter.StudentConverter;
import rs.fon.silab.dto.GroupDto;
import rs.fon.silab.dto.StudentDto;
import rs.fon.silab.model.Group;
import rs.fon.silab.model.Student;
import rs.fon.silab.repository.StudentRepository;
import rs.fon.silab.service.StudentService;

/**
 * @author goran
 * 
 * Service implementation which contains business logic for student entity
 */
@Service
public class StudentServiceImpl implements StudentService {

	/**
	 * Repository interface used for database interaction
	 */
	private final StudentRepository studentRepository;
	/**
	 * Group service implementation used to fetch data from group table
	 */
	private final GroupServiceImpl groupServiceImpl;
	/**
	 * Class used to convert group and groupDto
	 */
	private final GroupConverter groupConverter;

	@Autowired
	public StudentServiceImpl(final StudentRepository studentRepository, final GroupServiceImpl groupServiceImpl,
			final GroupConverter groupConverter) {
		this.studentRepository = studentRepository;
		this.groupServiceImpl = groupServiceImpl;
		this.groupConverter = groupConverter;
	}

	/**
	 * Class used to convert student and studentDto
	 */
	@Autowired
	StudentConverter studentConverter;

	/**
	 * @param StudentDto - StudentDto object we want to save in database
	 * @return StudentDto - StudentDto object which represents newly saved entity in db
	 * @throws  {@link ResponseStatusException} - if there is problem with saving student in db
	 * 
	 */
	@Override
	public StudentDto saveStudent(StudentDto studentDto) {
		try {
			Student student = this.studentConverter.toEntity(studentDto);
			List<Group> groups = new LinkedList<>();
			if (studentDto.getId() != null) {
				Student existingStudent = this.studentRepository.findById(studentDto.getId()).get();
				if (existingStudent != null) {
					throw new ResponseStatusException(HttpStatus.CONFLICT);
				}
			}
			for (GroupDto dto : studentDto.getGroups()) {
				try {
					GroupDto g = this.groupServiceImpl.getGroup(dto.getId());
					Group group = this.groupConverter.toEntity(g);
					group.setId(g.getId());
					groups.add(group);
				} catch (Exception e) {
					continue;
				}
			}
			student.setGroups(groups);
			Student savedStudent = this.studentRepository.save(student);
			return this.studentConverter.toDto(savedStudent);
		} catch (Exception e) {
			throw e;
		}
	}


	/**
	 * @param id of student we want to delete from database
	 * 
	 * @return 
	 * <ul>
	 * <li>true-if student is successfully deleted</li>
	 * <li>false-if there was a problem deleting student</li>
	 * </ul>
	 */
	@Override
	public boolean deleteStudent(Long id) {
		try {
			Student s = this.studentRepository.findById(id).get();
			if (s == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			this.studentRepository.delete(s);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @return List of all professors found in database
	 */
	@Override
	public List<StudentDto> getStudents() {
		try {
			List<Student> students = this.studentRepository.findAll();
			List<StudentDto> studentsDto = new LinkedList<>();
			students.forEach((student) -> {
				StudentDto dto = this.studentConverter.toDto(student);
				studentsDto.add(dto);
			});
			return studentsDto;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @param StudentDto object with existing id an updated data 
	 * @return StudentDto - Updated student entity represented with ProfessorDto
	 * @throws {@link ResponseStatusException} - if student with given id does not exist in db
	 */
	@Override
	public StudentDto updateStudent(StudentDto studentDto) {
		try {

			Student student = this.studentRepository.findById(studentDto.getId()).get();
			if (student == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			student.setBirthDate(studentDto.getBirthDate());
			student.setFirstName(studentDto.getFirstName());
			student.setLastName(studentDto.getLastName());
			student.setStudentStatus(studentDto.getStudentStatus());
			List<Group> groups = new LinkedList<>();
			for (GroupDto dto : studentDto.getGroups()) {
				try {
					GroupDto g = this.groupServiceImpl.getGroup(dto.getId());
					Group group = this.groupConverter.toEntity(g);
					group.setId(g.getId());
					groups.add(group);
				} catch (Exception e) {
					continue;
				}
			}
			student.setGroups(groups);
			Student savedStudent = this.studentRepository.save(student);
			return this.studentConverter.toDto(savedStudent);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param studentId - id of student we want to fetch from database
	 * @return StudentDto - StudentDto object with specific given id
	 * 
	 */
	@Override
	public StudentDto getStudent(Long id) {
		try {
			Student student = this.studentRepository.findById(id).get();
			if (student == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			return this.studentConverter.toDto(student);
		} catch (Exception e) {
			throw e;
		}
	}

}