package rs.fon.silab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.silab.dto.AreaDto;
import rs.fon.silab.service.impl.AreaServiceImpl;

@RestController
@RequestMapping("/api/v1/area")
/**
 * 
 * @author goran
 * Controller used to accept requests that
 * are related to Area entity
 */
public class AreaController {

	@Autowired
	private AreaServiceImpl areaServiceImpl;
	
	/**
	 * 
	 * @return List of AreaDto objects that represent area of specific course
	 */
	@GetMapping("all")
	List<AreaDto> getAllAreas(){
		return this.areaServiceImpl.getAllAreas();
	}
	
	/**
	 * 
	 * @param areaDto - Object representing area having specific structure
	 * @return AreaDto that is saved in database
	 */
	@PostMapping("save")
	AreaDto saveArea(@RequestBody AreaDto areaDto){
		return this.areaServiceImpl.saveArea(areaDto);
	}
	
	/**
	 * 
	 * @param id - Value of id of specific area that whose data we want to read from database
	 * @return AreaDto representing area with given id,containing all data specific to that area.
	 */
	@GetMapping("get")
	AreaDto getArea(@RequestParam("id")String id) {
		return this.areaServiceImpl.getArea(Long.valueOf(id));
	}
}