package rs.fon.silab.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rs.fon.silab.converter.CourseConverter;
import rs.fon.silab.converter.GroupConverter;
import rs.fon.silab.dto.CourseDto;
import rs.fon.silab.dto.GroupDto;
import rs.fon.silab.model.Course;
import rs.fon.silab.model.Group;
import rs.fon.silab.repository.GroupRepository;
import rs.fon.silab.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService{
	
	private final GroupRepository groupRepository;
	private final CourseServiceImpl courseServiceImpl;
	private final CourseConverter courseConverter;
	@Autowired
	public GroupServiceImpl(final GroupRepository groupRepository,final CourseServiceImpl courseServiceImpl,final CourseConverter courseConverter) {
		this.groupRepository=groupRepository;
		this.courseServiceImpl=courseServiceImpl;
		this.courseConverter=courseConverter;
	}
	
	@Autowired
	GroupConverter groupConverter;
	

	@Override
	public List<GroupDto> getAllGroups() {
		try {
			List<Group>groups=this.groupRepository.findAll();
			List<GroupDto>groupsDto=new ArrayList<>();
			groups.forEach((group)->{
				GroupDto dto=this.groupConverter.toDto(group);
				groupsDto.add(dto);
			});
			return groupsDto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public GroupDto saveGroup(GroupDto group) {
		//TODO:check problem with coruseConverter
		try {
			CourseDto courseDto=this.courseServiceImpl.getOneCourse(group.getCourse().getId());
			group.setCourse(courseDto);
			Group g=this.groupConverter.toEntity(group);
//			//g.setCourse(course);
//			g.setCourse(new Course(Long.valueOf(courseDto.getId()), courseDto.getCourseName(), courseDto.getSemester(),
//					courseDto.getStartDate(), courseDto.getEndDate(), courseDto.getImageUrl(),courseDto.getGroupCount(), courseDto.getLevel()));
			Group savedGroup=this.groupRepository.save(g);
			return this.groupConverter.toDto(savedGroup);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public GroupDto getGroup(Long id) {
		try {
			Optional<Group> group=this.groupRepository.findById(id);
			if(group.get()==null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Group with given id does not exist!");
			}
			return this.groupConverter.toDto(group.get());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Group with given id does not exist!");
		}
	}

	@Override
	public boolean deleteGroup(Long id) {
		try {
			Group g=this.groupRepository.findById(id).get();
			if(g==null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			this.groupRepository.delete(g);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}