package com.vikas.repository.student;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vikas.model.student.student;

@Repository
public interface StudentRepository extends JpaRepository<student, Integer>{	
	
	@Query("select s from student s order by s.FirstName")
	public List<student> getAllStudents();

}
