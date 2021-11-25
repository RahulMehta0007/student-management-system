package com.sms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sms.dto.TeacherDto;
import com.sms.exception.BadRequestException;
import com.sms.exception.ResourceNotFoundException;
import com.sms.model.Group;
import com.sms.model.Teacher;
import com.sms.model.Subject;
import com.sms.repository.GroupRepository;
import com.sms.repository.SubjectRepository;
import com.sms.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private ModelMapper mapper;

	public static final String NO_GROUP = "No Group Found";
	public static final String NO_SUBJECT = "No Subject Found";
	public static final String NO_TEACHER = "No Teacher Found";
	public static final String NO_ID = "No ID Found";

	public TeacherDto createTeacher(TeacherDto teacherDto) {
		
		if (ObjectUtils.isEmpty(teacherDto.getSubjectId()))
			throw new BadRequestException(NO_ID);
		
		Optional<Subject> optionalSubject = subjectRepository.findById(teacherDto.getSubjectId());
		if (!optionalSubject.isPresent())
			throw new ResourceNotFoundException(NO_SUBJECT);

		Optional<Group> optionalgroup = groupRepository.findById(teacherDto.getGroupId());
		if (!optionalgroup.isPresent())
			throw new ResourceNotFoundException(NO_GROUP);

		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(teacherDto, teacher);
		teacher.setGroupId(optionalgroup.get());
		teacher.setSubjectId(optionalSubject.get());

		teacher = teacherRepository.save(teacher);
		BeanUtils.copyProperties(teacher, teacherDto);

		return teacherDto;
	}

	public void updateTeacher(TeacherDto teacherDto) {
		
		if (ObjectUtils.isEmpty(teacherDto.getTeacherId()))
			throw new BadRequestException(NO_ID);
		
		Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherDto.getTeacherId());
		if (!optionalTeacher.isPresent())
			throw new ResourceNotFoundException(NO_TEACHER);

		Teacher teacher = optionalTeacher.get();
		BeanUtils.copyProperties(teacherDto, teacher);
		teacherRepository.save(teacher);

	}

	public TeacherDto getTeacher(int teacherId) {

		Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);

		if (!optionalTeacher.isPresent()) {
			throw new ResourceNotFoundException(NO_TEACHER);
		}
		TeacherDto teacherDto = new TeacherDto();
		Teacher teacher = optionalTeacher.get();

		BeanUtils.copyProperties(teacher, teacherDto);
		return teacherDto;

	}

	public void deleteTeacher(int teacherId) {

		Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);

		if (!optionalTeacher.isPresent()) {
			throw new ResourceNotFoundException(NO_TEACHER);
		}
		teacherRepository.deleteById(teacherId);

	}

	public List<TeacherDto> getAllTeachers() {

		List<Teacher> teacherList = teacherRepository.findAll();

		if (ObjectUtils.isEmpty(teacherList))
			throw new ResourceNotFoundException(NO_TEACHER);

		return teacherList.stream().map(teacher -> mapper.map(teacher, TeacherDto.class)).collect(Collectors.toList());
	}

}
