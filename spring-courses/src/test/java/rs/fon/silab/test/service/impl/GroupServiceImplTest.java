package rs.fon.silab.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;
import rs.fon.silab.dto.CourseDto;
import rs.fon.silab.dto.GroupDto;
import rs.fon.silab.model.Course;
import rs.fon.silab.model.Group;
import rs.fon.silab.repository.CourseRepository;
import rs.fon.silab.repository.GroupRepository;
import rs.fon.silab.service.impl.GroupServiceImpl;
import rs.fon.silab.util.Semester;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY )
public class GroupServiceImplTest {

	@Autowired
	private GroupServiceImpl groupServiceImpl;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	

	
	@BeforeEach
	void adGroupsToDatabase() {
		Course c1=new Course(Long.valueOf(1),"test1",Semester.summer,new Date(),new Date(),"medium",0,"test");
		Course c2=new Course(Long.valueOf(2),"test2",Semester.summer,new Date(),new Date(),"medium",0,"test");
		
		this.courseRepository.save(c1);
		this.courseRepository.save(c2);
		
		Group g1=new Group(Long.valueOf(1),"g1",10,new Date(),c1);
		Group g2=new Group(Long.valueOf(2),"g1",10,new Date(),c2);
		
		this.groupRepository.save(g1);
		this.groupRepository.save(g2);
	}


	@Test
	void shouldReturnListOfGroups(){
		List<GroupDto>groups=this.groupServiceImpl.getAllGroups();
		assertEquals(groups.size(), 2);
	}
	
	@Test
	void shouldThrowBadRequestException() {
		GroupDto g=new GroupDto(Long.valueOf(2),"asdf",new CourseDto(),new Date(),0);
		assertThrows(ResponseStatusException.class, ()->{this.groupServiceImpl.saveGroup(g);});
	}
	
	@Test
	void shouldReturnEntity() {
		GroupDto g=this.groupServiceImpl.getGroup(Long.valueOf(1));
		assertEquals(g.getId(), Long.valueOf(1));
		assertEquals(g.getGroupName(), "g1");	
	}
	
	@Test
	void shouldDeleteGroup() {
		assertTrue(this.groupServiceImpl.deleteGroup(Long.valueOf(1)));
	}
	
	@Test
	void shouldNotDeleteGroup() {
		assertFalse(this.groupServiceImpl.deleteGroup(Long.valueOf(5)));
	}

}