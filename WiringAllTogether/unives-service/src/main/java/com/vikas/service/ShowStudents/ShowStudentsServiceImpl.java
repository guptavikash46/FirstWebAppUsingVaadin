package com.vikas.service.ShowStudents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vikas.model.student.student;
import com.vikas.repository.student.StudentRepository;

@Service
@Transactional(readOnly=true)
public class ShowStudentsServiceImpl implements ShowStudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	public List<student> getAllStudents() {
		return studentRepository.getAllStudents();
	}

}
