package rs.fon.silab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.silab.dto.GroupDto;
import rs.fon.silab.service.impl.GroupServiceImpl;

/**
 * 
 * @author goran
 * Controller used to accept requests that
 * are related to Group entity
 */
@RestController
@RequestMapping("/api/v1/group")
public class GroupController {
	
	
	@Autowired
   GroupServiceImpl groupService;
	
	
	/**
	 * 
	 * @return - List of all groups found in database
	 */
	@GetMapping("all")
	 List<GroupDto> getAllGroups(){
		return this.groupService.getAllGroups();
	}
	
	/**
	 * 
	 * @param group - GroupDto that we want to save in database
	 * @return - GroupDto representing saved entity in database
	 */
	@PostMapping("save")
	 GroupDto saveGroup(@RequestBody GroupDto group) {
		return this.groupService.saveGroup(group);
	}
	
	/**
	 * 
	 * @param id - Value of id of specific group we want to load from database
	 * @return - GroupDto representing course with given id
	 */
	@GetMapping("get")
	GroupDto getGroup(@RequestParam String id) {
		return this.groupService.getGroup(Long.valueOf(id));
	}
	
	/**
	 * 
	 * @param id - Value of id of course we want to delete
	 * @return
	 * <ul>
	 * <li>true-if group is successfully deleted from database</li>
	 * <li>false-if deleting group failed</li>
	 * </ul>
	 */
	@DeleteMapping("delete")
	boolean deleteGroup(@RequestParam String id) {
		return this.groupService.deleteGroup(Long.valueOf(id));
	}
	
}