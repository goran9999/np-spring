package rs.fon.silab.converter;

import org.springframework.stereotype.Component;

import rs.fon.silab.dto.AreaDto;
import rs.fon.silab.model.Area;
import rs.fon.silab.model.Course;

@Component
public class AreaConverter implements Converter<AreaDto, Area>{

	@Override
	public AreaDto toDto(Area e) {
		return new AreaDto(e.getAreaName(), e.getClassesCount(),e.getCourse().getId());
	}

	@Override
	public Area toEntity(AreaDto d) {
		Course c=new Course();
		c.setId(d.getCourseId());
		return new Area(null, d.getAreaName(), d.getClassesCount(), c);
	}

}