package com.sms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GroupDto {
	
	@ApiModelProperty(notes="Group ID")
	private int groupId;
	@ApiModelProperty(notes="Group name")
	private String name;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GroupDto [groupId=" + groupId + ", name=" + name + "]";
	}

}
