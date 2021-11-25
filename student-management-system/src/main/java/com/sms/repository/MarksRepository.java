package com.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.model.Marks;

public interface MarksRepository extends JpaRepository<Marks, Integer> {

List<Marks> findByStudentStudentId(int studentId);

}
