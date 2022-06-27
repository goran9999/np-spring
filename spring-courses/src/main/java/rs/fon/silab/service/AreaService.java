package rs.fon.silab.service;

import java.util.List;

import rs.fon.silab.dto.AreaDto;

public interface AreaService {
	AreaDto getArea(Long areaId);
	 AreaDto saveArea(AreaDto areaDto);
	List<AreaDto> getAllAreas();
}