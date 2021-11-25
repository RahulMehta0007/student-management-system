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

import com.sms.dto.SubjectDto;
import com.sms.service.SubjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/subjects")
@Api(tags = {"Subject APIs"})
@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Authentication Failure"),
		@ApiResponse(code = 403, message = "FORBIDDEN"),
		@ApiResponse(code = 403, message = "Record Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error") })
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@PostMapping
	@ApiOperation(value = "Creates a subject", notes = "This API creates a subject", response = SubjectDto.class)
	public ResponseEntity<SubjectDto> createSubject(@RequestBody SubjectDto subjectDto) {

		return new ResponseEntity<SubjectDto>(subjectService.createSubject(subjectDto), HttpStatus.CREATED);

	}

	@PutMapping
	@ApiOperation(value = "Updates a Subject", notes = "This API updates a subject")
	public ResponseEntity<Object> updateSubject(@RequestBody SubjectDto subjectDto) {
		subjectService.updateSubject(subjectDto);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/{subjectId}")
	@ApiOperation(value = "Gets a subject by id", notes = "This API gets a subject by id", response = SubjectDto.class)
	public ResponseEntity<SubjectDto> getSubject(@PathVariable int subjectId) {
		return new ResponseEntity<SubjectDto>(subjectService.getSubject(subjectId), HttpStatus.OK);

	}

	@GetMapping("/list")
	@ApiOperation(value = "Gets a list of subjects", notes = "This API gets a list of subjects", response = SubjectDto.class,responseContainer="List")
	public ResponseEntity<List<SubjectDto>> getAllSubjects() {

		return new ResponseEntity<List<SubjectDto>>(subjectService.getAllSubjects(), HttpStatus.OK);

	}

	@DeleteMapping("/{subjectId}")
	@ApiOperation(value = "Delete a subject by id", notes = "This API deletes a subject by id")
	public ResponseEntity<Object> deleteSubject(@PathVariable int subjectId) {
		subjectService.deleteSubject(subjectId);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

}
