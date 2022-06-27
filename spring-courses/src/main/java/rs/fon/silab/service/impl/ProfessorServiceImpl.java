package rs.fon.silab.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rs.fon.silab.converter.GroupConverter;
import rs.fon.silab.converter.ProfessorConverter;
import rs.fon.silab.dto.GroupDto;
import rs.fon.silab.dto.ProfessorDto;
import rs.fon.silab.model.Group;
import rs.fon.silab.model.Professor;
import rs.fon.silab.repository.ProfessorRepository;
import rs.fon.silab.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	private final ProfessorRepository professorRepository;
	private final GroupServiceImpl groupServiceImpl;
	private final GroupConverter groupConverter;
	
	@Autowired
	public ProfessorServiceImpl(final ProfessorRepository professorRepository,final GroupServiceImpl groupServiceImpl,final GroupConverter groupConverter) {
		this.professorRepository=professorRepository;
		this.groupServiceImpl=groupServiceImpl;
		this.groupConverter=groupConverter;
	}
	
	@Autowired
	private ProfessorConverter professorConverter;
	

	@Override
	public ProfessorDto saveProfessor(ProfessorDto professor) {
		try {
			Professor p=this.professorConverter.toEntity(professor);
			List<Group> groups=new ArrayList<>();
			for (GroupDto group:professor.getGroups()) {
				try {
					GroupDto foundGroup=this.groupServiceImpl.getGroup(group.getId());
					Group g=this.groupConverter.toEntity(foundGroup);
					g.setId(foundGroup.getId());
					groups.add(g);
				} catch (Exception e) {
					continue;
				}
			}
			p.setGroups(groups);
			Professor savedProfessor=this.professorRepository.save(p);
			if(savedProfessor==null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Problem saving professor");
			}
			return this.professorConverter.toDto(savedProfessor);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ProfessorDto> getAllProfessors() {
		try {
			List<Professor>professors=this.professorRepository.findAll();
			List<ProfessorDto>professorsDto=new ArrayList<>();
			professors.forEach((professor)->{
				ProfessorDto p=this.professorConverter.toDto(professor);
				professorsDto.add(p);
			});
			return professorsDto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ProfessorDto getProfessor(Long id) {
		try {
			Optional<Professor> p=this.professorRepository.findById(id);
			return this.professorConverter.toDto(p.get());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ProfessorDto updateProfessor(ProfessorDto professor) {
		try {
			Professor updatedProfessor=this.professorRepository.findById(professor.getId()).get();
			if(updatedProfessor==null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			updatedProfessor.setId(professor.getId());
			updatedProfessor.setBithDate(professor.getBirthDate());
			updatedProfessor.setDegreeLevel(professor.getDegreeLevel());
			updatedProfessor.setFirstName(professor.getFirstName());
			updatedProfessor.setLastName(professor.getLastName());
			List<Group> groups=new ArrayList<>();
			for (GroupDto group:professor.getGroups()) {
				try {
					GroupDto foundGroup=this.groupServiceImpl.getGroup(group.getId());
					Group g=this.groupConverter.toEntity(foundGroup);
					g.setId(foundGroup.getId());
					groups.add(g);
				} catch (Exception e) {
					continue;
				}
			}
			updatedProfessor.setGroups(groups);
			Professor savedProfessor=this.professorRepository.save(updatedProfessor);
			return this.professorConverter.toDto(savedProfessor);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean deleteProfessor(Long id) {
		try {
			Professor p=this.professorRepository.findById(id).get();
			if(p==null) {
				return false;
			}
			this.professorRepository.delete(p);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}