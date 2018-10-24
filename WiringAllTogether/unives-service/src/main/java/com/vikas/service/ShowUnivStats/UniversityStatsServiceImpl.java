package com.vikas.service.ShowUnivStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vikas.repository.University.UniversityRepository;

@Service
@Transactional(readOnly=true)
public class UniversityStatsServiceImpl implements UniversityStatsService {

	@Autowired
	private UniversityRepository universityRepository;
	
	public Integer getNumOfStudentsForUnivesity(Integer universityId) {
		return universityRepository.getNumOfStudentForUniversity(universityId);
	}
	

}
