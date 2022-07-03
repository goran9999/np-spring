package rs.fon.silab.service.impl;


import java.util.LinkedList;
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

/**
 * @author goran
 * 
 * Service implementation which contains business logic for professor entity
 */
@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	/**
	 * Repository interface used for database interaction
	 */
	private final ProfessorRepository professorRepository;
	/**
	 * Group service implementation used to fetch data from group table
	 */
	private final GroupServiceImpl groupServiceImpl;
	
	/**
	 * Class used to convert group and groupDto
	 */
	private final GroupConverter groupConverter;
	
	@Autowired
	public ProfessorServiceImpl(final ProfessorRepository professorRepository,final GroupServiceImpl groupServiceImpl,final GroupConverter groupConverter) {
		this.professorRepository=professorRepository;
		this.groupServiceImpl=groupServiceImpl;
		this.groupConverter=groupConverter;
	}
	
	
	/**
	 * Class used to convert professor and professorDto
	 */
	@Autowired
	private ProfessorConverter professorConverter;
	

	/**
	 * @param ProfessorDto - ProfessorDto object we want to save in database
	 * @return ProfessorDto - ProfessorDto object which represents newly saved entity in db
	 * @throws  {@link ResponseStatusException} - if there is problem with saving professor in db
	 * 
	 */
	@Override
	public ProfessorDto saveProfessor(ProfessorDto professor) {
		try {
			Professor p=this.professorConverter.toEntity(professor);
			List<Group> groups=new LinkedList<>();
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

	/**
	 * @return List of all professors found in database
	 */
	@Override
	public List<ProfessorDto> getAllProfessors() {
		try {
			List<Professor>professors=this.professorRepository.findAll();
			List<ProfessorDto>professorsDto=new LinkedList<>();
			professors.forEach((professor)->{
				ProfessorDto p=this.professorConverter.toDto(professor);
				professorsDto.add(p);
			});
			return professorsDto;
		} catch (Exception e) {
			throw e;
		}
	}


	/**
	 * @param professorId - id of professor we want to fetch from database
	 * @return ProfessorDto - ProfessorDto object with specific given id
	 * 
	 */
	@Override
	public ProfessorDto getProfessor(Long id) {
		try {
			Optional<Professor> p=this.professorRepository.findById(id);
			return this.professorConverter.toDto(p.get());
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param ProfessorDto object with existing id an updated data 
	 * @return ProfessorDto - Updated professor entity represented with ProfessorDto
	 * @throws {@link ResponseStatusException} - if professor with given id does not exist in db
	 */
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
			List<Group> groups=new LinkedList<>();
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
	/**
	 * @param id of professor we want to delete from database
	 * 
	 * @return 
	 * <ul>
	 * <li>true-if professor is successfully deleted</li>
	 * <li>false-if there was a problem deleting professor</li>
	 * </ul>
	 */
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