package rs.fon.silab.converter;

import org.springframework.stereotype.Component;

import rs.fon.silab.dto.AreaDto;
import rs.fon.silab.model.Area;
import rs.fon.silab.model.Course;

/**
 * 
 * @author goran
 * 
 * Class used to enable converting between AreaDto and Area objects
 *
 */
@Component
public class AreaConverter implements Converter<AreaDto, Area>{

	/**
	 * Method that converts Area to AreaDto(data format that can be sent through network)
	 */
	@Override
	public AreaDto toDto(Area e) {
		return new AreaDto(e.getAreaName(), e.getClassesCount(),e.getCourse().getId());
	}
	
	/**
	 * Method that converts AreaDto to Area(data format that can be stored in database)
	 */
	@Override
	public Area toEntity(AreaDto d) {
		Course c=new Course();
		c.setId(d.getCourseId());
		return new Area(null, d.getAreaName(), d.getClassesCount(), c);
	}

}