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

import com.sms.dto.TeacherDto;
import com.sms.service.TeacherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/teachers")
@Api(tags = { "Teacher APIs" })
@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Authentication Failure"),
		@ApiResponse(code = 403, message = "FORBIDDEN"),
		@ApiResponse(code = 403, message = "Record Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error") })
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@PostMapping
	@ApiOperation(value = "Create a teacher", notes = "This API creates a teacher", response = TeacherDto.class)
	public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacherDto) {

		return new ResponseEntity<TeacherDto>(teacherService.createTeacher(teacherDto), HttpStatus.CREATED);

	}

	@PutMapping
	@ApiOperation(value = "Update a teacher", notes = "This API updates a teacher")
	public ResponseEntity<Object> updateTeacher(@RequestBody TeacherDto teacherDto) {
		teacherService.updateTeacher(teacherDto);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/{teacherId}")
	@ApiOperation(value = "Get a teacher by id", notes = "This API gets a teacher by id", response = TeacherDto.class)
	public ResponseEntity<TeacherDto> getTeacher(@PathVariable int teacherId) {

		return new ResponseEntity<TeacherDto>(teacherService.getTeacher(teacherId), HttpStatus.OK);

	}

	@GetMapping("/list")
	@ApiOperation(value = "Get a list of teachers", notes = "This API gets a list of teachers", response = TeacherDto.class, responseContainer = "List")
	public ResponseEntity<List<TeacherDto>> getAllTeacher() {

		return new ResponseEntity<List<TeacherDto>>(teacherService.getAllTeachers(), HttpStatus.OK);

	}

	@DeleteMapping("/{teacherId}")
	@ApiOperation(value = "Delete a teacher by id", notes = "This API deletes a teacher by id")
	public ResponseEntity<Object> deleteTeacher(@PathVariable int teacherId) {
		teacherService.deleteTeacher(teacherId);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

}
