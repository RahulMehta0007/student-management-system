package com.sms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sms.dto.GroupDto;
import com.sms.exception.BadRequestException;
import com.sms.exception.ResourceNotFoundException;
import com.sms.model.Group;
import com.sms.repository.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private ModelMapper mapper;

	public static final String NO_GROUP = "No Group Found";
	public static final String NO_ID = "No ID Found";

	public GroupDto createGroup(GroupDto groupDto) {

		Group group = new Group();
		BeanUtils.copyProperties(groupDto, group);
		group = groupRepository.save(group);
		BeanUtils.copyProperties(group, groupDto);

		return groupDto;

	}

	public void updateGroup(GroupDto groupDto) {
		
		if (ObjectUtils.isEmpty(groupDto.getGroupId()))
			throw new BadRequestException(NO_ID);

		
		Optional<Group> optionalGroup = groupRepository.findById(groupDto.getGroupId());

		if (!optionalGroup.isPresent()) {
			throw new ResourceNotFoundException(NO_GROUP);
		}

		Group group = optionalGroup.get();

		BeanUtils.copyProperties(groupDto, group);
		groupRepository.save(group);

	}

	public GroupDto getGroup(int groupId) {

		Optional<Group> optionalGroup = groupRepository.findById(groupId);

		if (!optionalGroup.isPresent()) {
			throw new ResourceNotFoundException(NO_GROUP);
		}
		GroupDto groupDto = new GroupDto();
		Group group = optionalGroup.get();

		BeanUtils.copyProperties(group, groupDto);
		return groupDto;

	}

	public void deleteGroup(int groupId) {

		Optional<Group> optionalGroup = groupRepository.findById(groupId);

		if (!optionalGroup.isPresent()) {
			throw new ResourceNotFoundException(NO_GROUP);
		}
		groupRepository.deleteById(groupId);

	}

	public List<GroupDto> getAllGroups() {

		List<Group> groupList = groupRepository.findAll();
		if (ObjectUtils.isEmpty(groupList))
			throw new ResourceNotFoundException(NO_GROUP);

		return groupList.stream().map(group -> mapper.map(group, GroupDto.class)).collect(Collectors.toList());
	}

}
