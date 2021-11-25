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

import com.sms.dto.MarksDto;
import com.sms.service.MarksService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/marks")
@Api(tags = { "Marks APIs" })
@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Authentication Failure"), @ApiResponse(code = 403, message = "FORBIDDEN"),
		@ApiResponse(code = 403, message = "Record Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error") })
public class MarksController {

	@Autowired
	private MarksService marksService;

	@PostMapping
	@ApiOperation(value = "Adds  Mark", notes = "This API adds a marks", response = MarksDto.class)
	public ResponseEntity<MarksDto> createMarks(@RequestBody MarksDto marksDto) {

		return new ResponseEntity<MarksDto>(marksService.createMarks(marksDto), HttpStatus.CREATED);

	}

	@PutMapping
	@ApiOperation(value = "Update Marks", notes = "This API updates a existing marks")
	public ResponseEntity<Object> updateMarks(@RequestBody MarksDto marksDto) {
		marksService.updateMarks(marksDto);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/{marksId}")
	@ApiOperation(value = "Get marks details", notes = "This API gets  marks based on ID", response = MarksDto.class)
	public ResponseEntity<MarksDto> getMarks(@PathVariable int marksId) {

		return new ResponseEntity<MarksDto>(marksService.getMarks(marksId), HttpStatus.OK);

	}

	@GetMapping("/list")
	@ApiOperation(value = "Get all  marks", notes = "This API lists all the marks details", response = MarksDto.class, responseContainer = "List")
	public ResponseEntity<List<MarksDto>> getAllMarks() {

		return new ResponseEntity<List<MarksDto>>(marksService.getAllmarkss(), HttpStatus.OK);

	}

	@DeleteMapping("/{marksId}")
	@ApiOperation(value = "deletes  mark by id", notes = "This API deletes marks based on id.")
	public ResponseEntity<Object> deleteMarks(@PathVariable int marksId) {
		marksService.deleteMarks(marksId);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}

	@ApiOperation(value = "Total Marks for a student", notes = "This API gives total marks of a student", response = MarksDto.class)
	@GetMapping("/total/{studentId}")
	public ResponseEntity<Object> getMarksByStudentId(@PathVariable int studentId) {
		return new ResponseEntity<>(marksService.getMarksForStudentId(studentId), HttpStatus.OK);
	}

	@ApiOperation(value = "View list of marks for each subject for a student", notes = "This API returns list of marks for each subject for a particular studentId", response = MarksDto.class)
	@GetMapping("/subjectList/{studentId}")
	public ResponseEntity<Object> getSubjectMarkListByStudentId(@PathVariable Integer studentId) {
		return new ResponseEntity<>(marksService.getSubjectMarkListByStudentId(studentId), HttpStatus.OK);
	}

}
