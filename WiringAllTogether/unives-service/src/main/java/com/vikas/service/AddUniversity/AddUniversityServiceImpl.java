package com.vikas.service.AddUniversity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vikas.model.University.University;
import com.vikas.repository.University.UniversityRepository;

@Service
@Transactional(readOnly=true)
public class AddUniversityServiceImpl implements AddUniversityService {

	@Autowired
	private UniversityRepository universityRepository;

	@Transactional
	public void saveUniversity(University universityDAO) {
          University university = new University();
          university.setUniversityName(universityDAO.getUniversityName());
          university.setUniversityCity(universityDAO.getUniversityCity());
          university.setUniversityCountry(universityDAO.getUniversityCountry());
          universityRepository.save(university);
	}
	


}
