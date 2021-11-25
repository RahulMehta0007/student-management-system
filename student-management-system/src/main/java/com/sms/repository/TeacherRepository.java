package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
