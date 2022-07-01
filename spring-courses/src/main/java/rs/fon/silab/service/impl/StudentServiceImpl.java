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

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final GroupServiceImpl groupServiceImpl;
	private final GroupConverter groupConverter;

	@Autowired
	public StudentServiceImpl(final StudentRepository studentRepository, final GroupServiceImpl groupServiceImpl,
			final GroupConverter groupConverter) {
		this.studentRepository = studentRepository;
		this.groupServiceImpl = groupServiceImpl;
		this.groupConverter = groupConverter;
	}

	@Autowired
	StudentConverter studentConverter;

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