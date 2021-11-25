package com.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dto.GroupDto;

import com.sms.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/groups")
@Api(tags = { "Group APIs" })
@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Authentication Failure"), @ApiResponse(code = 403, message = "FORBIDDEN"),
		@ApiResponse(code = 403, message = "Record Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error") })
public class GroupController {

	@Autowired
	private GroupService groupService;

	@PostMapping
	@ApiOperation(value = "Adds  Group", notes = "This API adds a group", response = GroupDto.class)
	public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {

		return new ResponseEntity<GroupDto>(groupService.createGroup(groupDto), HttpStatus.CREATED);

	}

	@PutMapping
	@ApiOperation(value = "Updates  a group", notes = "This API updates a group")
	public ResponseEntity<Object> updateGroup(@RequestBody GroupDto groupDto) {
		groupService.updateGroup(groupDto);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/{groupId}")
	@ApiOperation(value = "Gets group by id", notes = "This API gets a group by id", response = GroupDto.class)
	public ResponseEntity<GroupDto> getGroup(@PathVariable int groupId) {
		return new ResponseEntity<GroupDto>(groupService.getGroup(groupId), HttpStatus.OK);

	}

	@GetMapping("/list")
	@ApiOperation(value = "Gets list of groups", notes = "This API gets a list of groups", response = GroupDto.class, responseContainer = "List")
	public ResponseEntity<List<GroupDto>> getAllGroups() {

		return new ResponseEntity<List<GroupDto>>(groupService.getAllGroups(), HttpStatus.OK);

	}

	@DeleteMapping("/{groupId}")
	@ApiOperation(value = "deletes group by id", notes = "This API deletes a group by id")
	public ResponseEntity<Object> deleteGroup(@PathVariable int groupId) {
		groupService.deleteGroup(groupId);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

}
