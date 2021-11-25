package com.sms.service;

import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sms.dto.MarksDto;
import com.sms.exception.BadRequestException;
import com.sms.exception.ResourceNotFoundException;
import com.sms.model.Marks;
import com.sms.model.Student;
import com.sms.model.Subject;
import com.sms.repository.MarksRepository;
import com.sms.repository.StudentRepository;
import com.sms.repository.SubjectRepository;

@Service
public class MarksService {

	@Autowired
	private MarksRepository marksRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	public static final String NO_STUDENT = "No Student Found";
	public static final String NO_SUBJECT = "No Subject Found";
	public static final String NO_MARK = "No Mark Found";
	public static final String NO_ID = "No ID Found";

	@Autowired
	private ModelMapper mapper;

	public MarksDto createMarks(MarksDto markDto) {

		if (ObjectUtils.isEmpty(markDto.getStudentId()))
			throw new BadRequestException(NO_ID);

		Optional<Student> optionalStudent = studentRepository.findById(markDto.getStudentId());
		if (!optionalStudent.isPresent())
			throw new ResourceNotFoundException(NO_STUDENT);

		Optional<Subject> optionalSubject = subjectRepository.findById(markDto.getSubject_id());

		if (!optionalSubject.isPresent())
			throw new ResourceNotFoundException(NO_SUBJECT);

		Marks marks = new Marks();
		BeanUtils.copyProperties(markDto, marks);
		marks.setStudent(optionalStudent.get());
		marks.setSubjectId(optionalSubject.get());
		marks = marksRepository.save(marks);
		BeanUtils.copyProperties(marks, markDto);
		return markDto;
	}

	public void updateMarks(MarksDto markDto) {

		if (ObjectUtils.isEmpty(markDto.getMarksId()))
			throw new BadRequestException(NO_ID);

		Optional<Marks> optionalMarks = marksRepository.findById(markDto.getMarksId());

		if (!optionalMarks.isPresent())
			throw new ResourceNotFoundException(NO_MARK);

		Marks marks = optionalMarks.get();
		BeanUtils.copyProperties(markDto, marks);
		marksRepository.save(marks);

	}

	public MarksDto getMarks(int marksId) {

		Optional<Marks> optionalmarks = marksRepository.findById(marksId);

		if (!optionalmarks.isPresent()) {
			throw new ResourceNotFoundException(NO_MARK);
		}
		MarksDto marksDto = new MarksDto();
		Marks marks = optionalmarks.get();

		BeanUtils.copyProperties(marks, marksDto);
		return marksDto;

	}

	public void deleteMarks(int marksId) {

		Optional<Marks> optionalMarks = marksRepository.findById(marksId);

		if (!optionalMarks.isPresent()) {
			throw new ResourceNotFoundException(NO_MARK);
		}
		marksRepository.deleteById(marksId);

	}

	public List<MarksDto> getAllmarkss() {

		List<Marks> marksList = marksRepository.findAll();
		if (ObjectUtils.isEmpty(marksList))
			throw new ResourceNotFoundException(NO_MARK);

		return marksList.stream().map(marks -> mapper.map(marks, MarksDto.class)).collect(Collectors.toList());
	}


	public int getMarksForStudentId(int studentId) {
		List<MarksDto> markList = this.getSubjectMarkListByStudentId(studentId);
		ToIntFunction<MarksDto> sum = m -> m.getMark();
		return markList.stream().collect(Collectors.summingInt(sum));
	}

	public List<MarksDto> getSubjectMarkListByStudentId(int studentId) {
		if (ObjectUtils.isEmpty(studentId))
			throw new BadRequestException(NO_ID);

		List<Marks> markList = marksRepository.findByStudentStudentId(studentId);
		if (ObjectUtils.isEmpty(markList))
			throw new ResourceNotFoundException(NO_MARK);
		
		List<MarksDto> markDtoList = markList.stream()
											.map(marks -> mapper.map(marks, MarksDto.class))
											.collect(Collectors.toList());
		return markDtoList;
	}

	
	
	
}
