package rs.fon.silab.converter;

import java.util.Date;

import org.springframework.stereotype.Component;

import rs.fon.silab.dto.CourseDto;
import rs.fon.silab.model.Course;

@Component
public class CourseConverter implements Converter<CourseDto, Course>{

	@Override
	public CourseDto toDto(Course e) {
		CourseDto dto=new CourseDto();
		dto.setCourseName(e.getCourseName());
		dto.setEndDate(e.getEndDate());
		dto.setGroupCount(e.getGroupsCount());
		dto.setLevel(e.getLevel());
		dto.setSemester(e.getSemester());
		dto.setStartDate(e.getStartDate());
		dto.setId(e.getId());
		
		
		return dto;
	}

	@Override
	public Course toEntity(CourseDto d) {
		System.out.println("CONVERTING...");
		Course c=new Course();
		c.setCourseName(d.getCourseName());
		c.setEndDate(new Date(d.getEndDate().getTime()));
		c.setStartDate(new Date(d.getStartDate().getTime()));
		c.setGroupsCount(d.getGroupCount());
		c.setLevel(d.getLevel());
		c.setSemester(d.getSemester());
		
		return c;
	}

}