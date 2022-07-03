package rs.fon.silab.service.impl;


import java.util.LinkedList;
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

/**
 * @author goran
 * 
 * Service implementation which contains business logic for group entity
 */
@Service
public class GroupServiceImpl implements GroupService{
	
	/**
	 * Repository interface used for database interaction
	 */
	private final GroupRepository groupRepository;
	/**
	 * Course service implementation used to fetch data from course table
	 */
	private final CourseServiceImpl courseServiceImpl;
	/**
	 * Class used to convert course and coursedto
	 */
	private final CourseConverter courseConverter;
	@Autowired
	public GroupServiceImpl(final GroupRepository groupRepository,final CourseServiceImpl courseServiceImpl,final CourseConverter courseConverter) {
		this.groupRepository=groupRepository;
		this.courseServiceImpl=courseServiceImpl;
		this.courseConverter=courseConverter;
	}
	
	/**
	 * Class used to convert group and groupDto
	 */
	@Autowired
	GroupConverter groupConverter;
	

	/**
	 * @return List of all groups found in database
	 * @throws - {@link ResponseStatusException} - if there is no saved groups in db
	 */
	@Override
	public List<GroupDto> getAllGroups() {
		try {
			List<Group>groups=this.groupRepository.findAll();
			List<GroupDto>groupsDto=new LinkedList<>();
			groups.forEach((group)->{
				GroupDto dto=this.groupConverter.toDto(group);
				groupsDto.add(dto);
			});
			return groupsDto;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param GroupDto - GroupDto object we want to save in database
	 * @return GroupDto - GroupDto object which represents newly saved entity in db
	 * @throws  {@link ResponseStatusException} - if there is problem with saving group in db
	 * 
	 */
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

	/**
	 * @param groupId - id of group we want to fetch from database
	 * @return GroupDto - GroupDto object with specific given id
	 * @throws  {@link ResponseStatusException} if group is not found in database or there is problem with loading group
	 */
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

	/**
	 * @param id of group we want to delete from database
	 * 
	 * @return 
	 * <ul>
	 * <li>true-if group is successfully deleted</li>
	 * <li>false-if there was a problem deleting group</li>
	 * </ul>
	 */
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