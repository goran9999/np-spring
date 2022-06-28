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

@RestController
@RequestMapping("api/v1/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorServiceImpl professorServiceImpl;
	
	
	@GetMapping("get")
	ProfessorDto getProfessor(@RequestParam String id) {
		return this.professorServiceImpl.getProfessor(Long.valueOf(id));
	}
	
	@GetMapping("all")
	List<ProfessorDto>getAllProfessors(){
		return this.professorServiceImpl.getAllProfessors();
	}
	
	@PostMapping("save")
	ProfessorDto saveProfessor(@RequestBody ProfessorDto professorDto) {
		return this.professorServiceImpl.saveProfessor(professorDto);
	}
	
	@PatchMapping("update")
	ProfessorDto updateProfessor(@RequestBody ProfessorDto professorDto) {
		System.out.println(professorDto);
		return this.professorServiceImpl.updateProfessor(professorDto);
	}
	
	@DeleteMapping("delete")
	boolean deleteProfessor(@RequestParam String id) {
		return this.professorServiceImpl.deleteProfessor(Long.valueOf(id));
	}
}