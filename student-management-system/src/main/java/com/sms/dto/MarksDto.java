package com.sms.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class MarksDto {

	@ApiModelProperty(notes="Mark ID")
	private int marksId;
	@ApiModelProperty(notes="Student ID")
	private int studentId;
	@ApiModelProperty(notes="Subject ID")
	private int subject_id;
	@ApiModelProperty(notes="Date")
	private Date date;
	@ApiModelProperty(notes="marks")
	private int mark;

	public int getMarksId() {
		return marksId;
	}

	public void setMarksId(int marksId) {
		this.marksId = marksId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "MarksDto [marksId=" + marksId + ", studentId=" + studentId + ", subject_id=" + subject_id + ", date="
				+ date + ", mark=" + mark + "]";
	}

}
