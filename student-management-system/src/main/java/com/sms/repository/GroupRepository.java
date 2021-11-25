package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.model.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

}
