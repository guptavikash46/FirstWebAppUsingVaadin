package com.vikas.service.ShowUniversities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vikas.model.University.University;
import com.vikas.repository.University.UniversityRepository;

@Service
@Transactional(readOnly=true)
public class ShowUniversitiesServiceImpl implements ShowUniversitiesService{

	@Autowired
	private UniversityRepository universityRepository;
	
	public List<University> getAllUnivNames() {
		return universityRepository.getAllUniversityName();
	}

}
