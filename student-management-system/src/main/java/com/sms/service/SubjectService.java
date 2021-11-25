package com.sms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sms.dto.SubjectDto;
import com.sms.exception.ResourceNotFoundException;
import com.sms.model.Subject;
import com.sms.repository.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private ModelMapper mapper;

	public static final String NO_SUBJECT = "No Subject Found";

	public SubjectDto createSubject(SubjectDto subjectDto) {

		Subject subject = new Subject();
		BeanUtils.copyProperties(subjectDto, subject);
		subject = subjectRepository.save(subject);
		BeanUtils.copyProperties(subject, subjectDto);

		return subjectDto;

	}

	public void updateSubject(SubjectDto subjectDto) {

		Optional<Subject> optionalSubject = subjectRepository.findById(subjectDto.getSubjectId());

		if (!optionalSubject.isPresent()) {
			throw new ResourceNotFoundException(NO_SUBJECT);
		}

		Subject subject = optionalSubject.get();

		BeanUtils.copyProperties(subjectDto, subject);
		subjectRepository.save(subject);

	}

	public SubjectDto getSubject(int subjectId) {

		Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);

		if (!optionalSubject.isPresent()) {
			throw new ResourceNotFoundException(NO_SUBJECT);
		}
		SubjectDto subjectDto = new SubjectDto();
		Subject subject = optionalSubject.get();

		BeanUtils.copyProperties(subject, subjectDto);
		return subjectDto;

	}

	public void deleteSubject(int subjectId) {

		Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);

		if (!optionalSubject.isPresent()) {
			throw new ResourceNotFoundException(NO_SUBJECT);
		}
		subjectRepository.deleteById(subjectId);

	}

	public List<SubjectDto> getAllSubjects() {

		List<Subject> subjectList = subjectRepository.findAll();
		if (ObjectUtils.isEmpty(subjectList))
			throw new ResourceNotFoundException(NO_SUBJECT);

		return subjectList.stream().map(subject -> mapper.map(subject, SubjectDto.class)).collect(Collectors.toList());
	}

}
