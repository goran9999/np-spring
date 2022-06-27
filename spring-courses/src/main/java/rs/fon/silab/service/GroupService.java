package rs.fon.silab.service;

import java.util.List;


import rs.fon.silab.dto.GroupDto;


public interface GroupService{

	List<GroupDto> getAllGroups();
	GroupDto saveGroup(GroupDto group);
	GroupDto getGroup(Long id);
	boolean deleteGroup(Long id);
}