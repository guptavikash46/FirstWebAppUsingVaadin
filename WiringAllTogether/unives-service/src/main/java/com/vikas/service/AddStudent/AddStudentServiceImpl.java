package com.vikas.service.AddStudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vikas.model.student.student;
import com.vikas.repository.student.StudentRepository;

@Service
@Transactional(readOnly=true)
public class AddStudentServiceImpl implements AddStudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Transactional
	public void saveStudent(student studentDAO) {
		student student = new student();
		
		student.setFirstName(studentDAO.getFirstName());
		student.setLastName(studentDAO.getLastName());
		student.setAge(studentDAO.getAge());
		student.setEmail(studentDAO.getEmail());
		student.setGender(studentDAO.getGender());
		student.setUniversity(studentDAO.getUniversity());
		
		studentRepository.save(student);
		
	}

}
