package com.sms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
public class Subject {

	@Id
	@Column(name="subject_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subjectId;

	@Column(name = "title")
	private String title;
	
	@OneToMany(mappedBy="subjectId",cascade=CascadeType.REMOVE)
	private List<Teacher> teachers;
	
	@OneToMany(mappedBy="subjectId",cascade=CascadeType.REMOVE)
	private List<Marks> marks;

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

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Marks> getMarks() {
		return marks;
	}

	public void setMarks(List<Marks> marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", title=" + title + ", teachers=" + teachers + ", marks=" + marks
				+ "]";
	}
	
	

}
