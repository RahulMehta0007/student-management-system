package com.sms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TeacherDto {
	@ApiModelProperty(notes="Subject ID")
	private int subjectId;
	@ApiModelProperty(notes="Teacher ID")
	private int teacherId;
	@ApiModelProperty(notes="Group ID")
	private int groupId;

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "TeacherDto [subjectId=" + subjectId + ", teacherId=" + teacherId + ", groupId=" + groupId + "]";
	}

}
