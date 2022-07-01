package rs.fon.silab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.silab.dto.ProfessorDto;
import rs.fon.silab.service.impl.ProfessorServiceImpl;

/**
 * 
 * @author goran
 * Controller used to accept requests that
 * are related to Professor entity
 */
@RestController
@RequestMapping("api/v1/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorServiceImpl professorServiceImpl;
	
	/**
	 * 
	 * @param id - Value of id of specific professor we want to load from database
	 * @return - ProfessorDto representing course with given id
	 */
	@GetMapping("get")
	ProfessorDto getProfessor(@RequestParam String id) {
		return this.professorServiceImpl.getProfessor(Long.valueOf(id));
	}
	
	/**
	 * 
	 * @return - List of all professors found in database
	 */
	@GetMapping("all")
	List<ProfessorDto>getAllProfessors(){
		return this.professorServiceImpl.getAllProfessors();
	}
	
	
	/**
	 * 
	 * @param professor - ProfessorDto that we want to save in database
	 * @return - ProfessorDto representing saved entity in database
	 */
	@PostMapping("save")
	ProfessorDto saveProfessor(@RequestBody ProfessorDto professorDto) {
		return this.professorServiceImpl.saveProfessor(professorDto);
	}
	
	/**
	 * 
	 * @param professorDto-ProfessorDto having id of existing professor entity from
	 * database whose data should be updated
	 * @return - Updated old professor data
	 */
	@PatchMapping("update")
	ProfessorDto updateProfessor(@RequestBody ProfessorDto professorDto) {
		System.out.println(professorDto);
		return this.professorServiceImpl.updateProfessor(professorDto);
	}
	
	/**
	 * 
	 * @param id - Value of id of professor we want to delete
	 * @return
	 * <ul>
	 * <li>true-if professor is successfully deleted from database</li>
	 * <li>false-if deleting professor failed</li>
	 * </ul>
	 */
	@DeleteMapping("delete")
	boolean deleteProfessor(@RequestParam String id) {
		return this.professorServiceImpl.deleteProfessor(Long.valueOf(id));
	}
}