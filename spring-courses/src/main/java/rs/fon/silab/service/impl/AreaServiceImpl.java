package rs.fon.silab.service.impl;

import java.util.ArrayList;
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

@Service
public class AreaServiceImpl implements AreaService {

	private final AreaRepository areaRepository;
	private final CourseServiceImpl courseServiceImpl;

	@Autowired
	public AreaServiceImpl(final AreaRepository areaRepository, final CourseServiceImpl courseServiceImpl) {
		this.areaRepository = areaRepository;
		this.courseServiceImpl = courseServiceImpl;
	}

	@Autowired
	AreaConverter areaConverter;

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

	@Override
	public List<AreaDto> getAllAreas() {
		try {
			List<Area> areas = this.areaRepository.findAll();
			List<AreaDto> areaDtos = new ArrayList<>();
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