package rs.fon.silab.converter;



import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.fon.silab.dto.GroupDto;
import rs.fon.silab.dto.StudentDto;
import rs.fon.silab.model.Group;
import rs.fon.silab.model.Student;

/**
 * 
 * @author goran
 * 
 * Class used to enable converting between StudentDto and Student objects
 *
 */
@Component
public class StudentConverter implements Converter<StudentDto, Student>{
	
	@Autowired
	private GroupConverter converter;

	/**
	 * Method that converts StudentDto and Student(data format that can be sent through network)
	 */
	@Override
	public StudentDto toDto(Student e) {
		List<GroupDto>groups=new LinkedList<GroupDto>();
		e.getGroups().forEach((group)->{
			GroupDto g=this.converter.toDto(group);
			groups.add(g);
		});
		return new StudentDto(e.getId(), e.getFirstName(),e.getLastName(),e.getBirthDate(),e.getStudentStatus(),groups);
	}

	/**
	 * Method that converts StudentDto and Student(data format that can be stored in database)
	 */
	@Override
	public Student toEntity(StudentDto d) {
		return new Student(d.getId(), d.getFirstName(), d.getLastName(), d.getBirthDate(), d.getStudentStatus());
	}

}