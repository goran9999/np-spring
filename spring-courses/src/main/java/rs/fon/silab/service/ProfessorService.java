package rs.fon.silab.service;

import java.util.List;

import rs.fon.silab.dto.ProfessorDto;


public interface ProfessorService {
	ProfessorDto saveProfessor(ProfessorDto professor);
	List<ProfessorDto> getAllProfessors();
	ProfessorDto getProfessor(Long id);
	ProfessorDto updateProfessor(ProfessorDto professor);
	boolean deleteProfessor(Long id);
}