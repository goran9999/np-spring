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


@RestController
@RequestMapping("/api/v1/group")
public class GroupController {
	
	
	@Autowired
   GroupServiceImpl groupService;
	
	
	
	@GetMapping("all")
	 List<GroupDto> getAllGroups(){
		return this.groupService.getAllGroups();
	}
	
	@PostMapping("save")
	 GroupDto saveGroup(@RequestBody GroupDto group) {
		return this.groupService.saveGroup(group);
	}
	
	@GetMapping("get")
	GroupDto getGroup(@RequestParam String id) {
		return this.groupService.getGroup(Long.valueOf(id));
	}
	
	@DeleteMapping("delete")
	boolean deleteGroup(@RequestParam String id) {
		return this.groupService.deleteGroup(Long.valueOf(id));
	}
	
}