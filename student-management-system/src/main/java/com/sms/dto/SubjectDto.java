package com.sms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SubjectDto {
	@ApiModelProperty(notes="Subject ID")
	private int subjectId;
	@ApiModelProperty(notes="Title")
	private String title;

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "SubjectDto [subjectId=" + subjectId + ", title=" + title + "]";
	}

}
