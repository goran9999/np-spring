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
public class AreaController {

	@Autowired
	private AreaServiceImpl areaServiceImpl;
	
	@GetMapping("all")
	List<AreaDto> getAllAreas(){
		return this.areaServiceImpl.getAllAreas();
	}
	
	@PostMapping("save")
	AreaDto saveArea(@RequestBody AreaDto areaDto){
		return this.areaServiceImpl.saveArea(areaDto);
	}
	
	@GetMapping("get")
	AreaDto getArea(@RequestParam("id")String id) {
		return this.areaServiceImpl.getArea(Long.valueOf(id));
	}
}