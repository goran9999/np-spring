package rs.fon.silab.converter;



import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.fon.silab.dto.GroupDto;
import rs.fon.silab.dto.ProfessorDto;
import rs.fon.silab.model.Professor;

/**
 * 
 * @author goran
 * 
 * Class used to enable converting between ProfessorDto and Professor objects
 *
 */
@Component
public class ProfessorConverter implements Converter<ProfessorDto, Professor>{

	@Autowired
	GroupConverter groupConverter;
	
	/**
	 * Method that converts ProfessorDto and Professor(data format that can be sent through network)
	 */
	@Override
	public ProfessorDto toDto(Professor e) {
		List<GroupDto>groups=new LinkedList<GroupDto>();
		e.getGroups().forEach((group)->{
			GroupDto dto=this.groupConverter.toDto(group);
			groups.add(dto);
		});
		return new ProfessorDto(e.getId(),e.getFirstName(),e.getLastName(),e.getBithDate(),e.getDegreeLevel(),groups);
	}

	/**
	 * Method that converts  ProfessorDto and Professor(data format that can be stored in database)
	 */
	@Override
	public Professor toEntity(ProfessorDto d) {
		return new Professor(d.getId(), d.getFirstName(), d.getLastName(), d.getBirthDate(), d.getDegreeLevel());
	}

}