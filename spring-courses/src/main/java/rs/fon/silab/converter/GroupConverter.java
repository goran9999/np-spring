package rs.fon.silab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.fon.silab.dto.GroupDto;
import rs.fon.silab.model.Course;
import rs.fon.silab.model.Group;

/**
 * 
 * @author goran
 * 
 * Class used to enable converting between GroupDto and Group objects
 *
 */
@Component
public class GroupConverter implements Converter<GroupDto, Group> {

	@Autowired
	CourseConverter courseConverter;
	

	/**
	 * Method that converts Group to GroupDto(data format that can be sent through network)
	 */
	@Override
	public GroupDto toDto(Group e) {
		return new GroupDto(e.getId(),e.getName(),courseConverter.toDto(e.getCourse()),e.getCreatedAt(),e.getStudentsCount());
	}

	/**
	 * Method that converts Group to GroupDto(data format that can be stored in database)
	 */
	@Override
	public Group toEntity(GroupDto d) {
		Course c=new Course();
		c.setId(d.getCourse().getId());
		Group g=new Group();
		g.setName(d.getGroupName());
		g.setCreatedAt(d.getCreatedAt());
		g.setStudentsCount(d.getStudentsCount());
		g.setCourse(c);
		return g;
	}

}