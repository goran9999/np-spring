package rs.fon.silab.service.impl;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rs.fon.silab.converter.AreaConverter;
import rs.fon.silab.dto.AreaDto;
import rs.fon.silab.dto.CourseDto;
import rs.fon.silab.model.Area;
import rs.fon.silab.model.Course;
import rs.fon.silab.repository.AreaRepository;
import rs.fon.silab.service.AreaService;

/**
 * @author goran
 * 
 * Service implementation which contains business logic for area entity
 */
@Service
public class AreaServiceImpl implements AreaService {

	/**
	 * Repository interface used for database interaction
	 */
	private final AreaRepository areaRepository;
	/**
	 * Course service implementation used to fetch data from course table
	 */
	private final CourseServiceImpl courseServiceImpl;

	/**
	 * Parameter constructor
	 * @param areaRepository
	 * @param courseServiceImpl
	 */
	@Autowired
	public AreaServiceImpl(final AreaRepository areaRepository, final CourseServiceImpl courseServiceImpl) {
		this.areaRepository = areaRepository;
		this.courseServiceImpl = courseServiceImpl;
	}

	/**
	 * Class used to convert area and areadto
	 */
	@Autowired
	AreaConverter areaConverter;

	/**
	 * @param areaId - id of area we want to fetch from database
	 * @return AreaDto - AreaDto object with specific given id
	 * @throws  {@link ResponseStatusException} if area is not found in database
	 */
	
	@Override
	public AreaDto getArea(Long areaId) {
		try {
			Optional<Area> area = this.areaRepository.findById(areaId);
			if (area.get() == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			return this.areaConverter.toDto(area.get());
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param AreaDto - AreaDto object we want to save in database
	 * @return AreaDto - AreaDto object which represents newly saved entity in db
	 * @throws  {@link ResponseStatusException}
	 * <ul>
	 * <li>if related course does not exist</li>
	 * <li>if there is problem with savind area in databaset</li>
	 * </ul>
	 */
	@Override
	public AreaDto saveArea(AreaDto areaDto) {
		try {
			CourseDto courseDto = this.courseServiceImpl.getOneCourse(areaDto.getCourseId());
			if(courseDto==null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Course with given id does not exist!");
			}
			areaDto.setCourseId(courseDto.getId());
			Area area = this.areaConverter.toEntity(areaDto);
			Area savedArea = this.areaRepository.save(area);
			if (savedArea == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problem with saving area");
			}
			return this.areaConverter.toDto(savedArea);
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

	/**
	 * @return List of all areas found in database
	 */
	@Override
	public List<AreaDto> getAllAreas() {
		try {
			List<Area> areas = this.areaRepository.findAll();
			List<AreaDto> areaDtos = new LinkedList<>();
			areas.forEach((area) -> {
				AreaDto convertedArea = this.areaConverter.toDto(area);
				areaDtos.add(convertedArea);
			});
			return areaDtos;
		} catch (Exception e) {
			throw e;
		}
	}

}