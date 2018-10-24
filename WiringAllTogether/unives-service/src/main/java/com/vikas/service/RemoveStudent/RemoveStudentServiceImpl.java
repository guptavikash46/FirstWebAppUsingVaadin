package com.vikas.service.RemoveStudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vikas.model.student.student;
import com.vikas.repository.student.StudentRepository;

@Service
@Transactional(readOnly=true)
public class RemoveStudentServiceImpl  implements RemoveStudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Transactional
	public void removeStudent(student stud) {
		studentRepository.delete(stud);
	}

}
