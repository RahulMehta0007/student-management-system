package com.sms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sms.dto.StudentDto;
import com.sms.exception.BadRequestException;
import com.sms.exception.ResourceNotFoundException;
import com.sms.model.Group;
import com.sms.model.Student;
import com.sms.repository.GroupRepository;
import com.sms.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private ModelMapper mapper;

	public static final String NO_STUDENT = "No Student Found";
	public static final String NO_ID = "No ID Found";

	public StudentDto createStudent(StudentDto studentDto) {

		Optional<Group> group = groupRepository.findById(studentDto.getGroupId());

		if (!group.isPresent()) {
			throw new ResourceNotFoundException(NO_STUDENT);
		}

		Student student = new Student();
		BeanUtils.copyProperties(studentDto, student);
		student.setGroup(group.get());
		student = studentRepository.save(student);
		BeanUtils.copyProperties(student, studentDto);

		return studentDto;

	}

	public void updateStudent(StudentDto studentDto) {

		if (ObjectUtils.isEmpty(studentDto.getStudentId()))
			throw new BadRequestException(NO_ID);

		Optional<Student> optionalStudent = studentRepository.findById(studentDto.getStudentId());

		if (!optionalStudent.isPresent()) {
			throw new ResourceNotFoundException(NO_STUDENT);
		}

		Student student = optionalStudent.get();

		BeanUtils.copyProperties(studentDto, student);
		studentRepository.save(student);
	}

	public StudentDto getStudent(int studentId) {

		Optional<Student> optionalStudent = studentRepository.findById(studentId);

		if (!optionalStudent.isPresent()) {
			throw new ResourceNotFoundException(NO_STUDENT);
		}
		StudentDto studentDto = new StudentDto();
		Student student = optionalStudent.get();
		BeanUtils.copyProperties(student, studentDto);

		return studentDto;
	}

	public List<StudentDto> getAllStudents() {

		List<Student> studentList = studentRepository.findAll();
		if (ObjectUtils.isEmpty(studentList))
			throw new ResourceNotFoundException(NO_STUDENT);

		return studentList.stream().map(student -> mapper.map(student, StudentDto.class)).collect(Collectors.toList());
	}

	public void deleteStudent(int studentId) {

		Optional<Student> optionalStudent = studentRepository.findById(studentId);

		if (!optionalStudent.isPresent()) {
			throw new ResourceNotFoundException(NO_STUDENT);
		}
		studentRepository.deleteById(studentId);

	}

}
