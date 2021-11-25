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

import com.sms.dto.StudentDto;
import com.sms.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/students")
@Api(tags = {"Student APIs"})
@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Authentication Failure"),
		@ApiResponse(code = 403, message = "FORBIDDEN"),
		@ApiResponse(code = 403, message = "Record Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error") })
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping
	@ApiOperation(value = "Creates a student", notes = "This API creates a student", response = StudentDto.class)
	public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {

		return new ResponseEntity<StudentDto>(studentService.createStudent(studentDto), HttpStatus.CREATED);

	}

	@PutMapping
	@ApiOperation(value = "Updates a student", notes = "This API updates a student")
	public ResponseEntity<Object> updateStudent(@RequestBody StudentDto studentDto) {
		studentService.updateStudent(studentDto);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/{studentId}")
	@ApiOperation(value = "Get a student by id", notes = "This API gets a student by id", response = StudentDto.class)
	public ResponseEntity<StudentDto> getStudent(@PathVariable int studentId) {

		return new ResponseEntity<StudentDto>(studentService.getStudent(studentId), HttpStatus.OK);

	}

	@GetMapping("/list")
	@ApiOperation(value = "Get a student by id", notes = "This API gets a student by id", response = StudentDto.class,responseContainer="List")
	public ResponseEntity<List<StudentDto>> getAllStudent() {

		return new ResponseEntity<List<StudentDto>>(studentService.getAllStudents(), HttpStatus.OK);

	}

	@DeleteMapping("/{studentId}")
	@ApiOperation(value = "Deletes a student by id", notes = "This API deletes a student by id")
	public ResponseEntity<Object> deleteStudent(@PathVariable int studentId) {
		studentService.deleteStudent(studentId);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

}
